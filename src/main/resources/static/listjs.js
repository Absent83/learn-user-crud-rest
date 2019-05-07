$(document).ready(function () {
    console.log("start loading users and roles");

    var api_url = 'http://localhost:8080/api';

    var isUsersReady = false;
    var isRolesReady = false;

    var usersJsonFromServer;
    var rolesJsonFromServer;


    $.ajax({
        url: api_url + "/users",
        contentType: "application/json",
        dataType: 'json',
        success: function (result) {
            usersJsonFromServer = result;
            isUsersReady = true;
            console.log(result);
            makeList();
        }
    });


    $.ajax({
        url: api_url + "/roles",
        contentType: "application/json",
        dataType: 'json',
        success: function (result) {
            rolesJsonFromServer = result;
            isRolesReady = true;
            console.log(result);
            makeList();
        }
    });


    function makeList() {

        if (isRolesReady && isUsersReady) {
            var trHTML = '';
            $.each(usersJsonFromServer, function (i, user) {

                var tdROLES = '';

                $.each(user.roles, function (k, role) {
                    tdROLES += '<p>' + role.authority + '</p>'
                });

                trHTML =
                    '<tr>' +

                    '<td>' + user.id + '</td>' +
                    '<td>' + user.username + '</td>' +
                    '<td>' + user.firstName + '</td>' +
                    '<td>' + user.email + '</td>' +
                    '<td>' + tdROLES + '</td>' +
                    '<td>' + user.password + '</td>' +
                    '<td>' +
                    '<button ' +
                    'class="btn btn-primary" ' +
                    'id="buttonUserEdit' + user.id + '" ' +
                    'data-target="#usereditmodaljs" ' +
                    'data-toggle="modal" ' +
                    'type="button">' +
                    'Edit' +
                    '</button>' +
                    '</td>' +
                    '<td>' +
                    '<button class="btn btn-danger" type="button">Delete</button>' +
                    '</td>' +

                    '</tr>';

                $('#usertable').append(trHTML);

                $('#buttonUserEdit' + user.id).data('user', user);

            });


            $('#usereditmodaljs').on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget); // Button that triggered the modal


                var modalheader = $(event.currentTarget).find('.modal-header');
                modalheader.find('.modal-title').text('Edit user ' + button.data('firstname'));


                var modalbody = $(event.currentTarget).find('.modal-body');
                var user = button.data('user');

                modalbody.find('form[id="userform"]').attr('action', '/api/users/' + user.id);
                modalbody.find('input[id="id"]').val(user.id);
                modalbody.find('input[id="username"]').val(user.username);
                modalbody.find('input[id="firstName"]').val(user.firstName);
                modalbody.find('input[id="email"]').val(user.email);
                modalbody.find('input[id="password"]').val(user.password);

                modalbody.find('select[id="roles"]').html('');

                $.each(rolesJsonFromServer, function (i, role) {

                    var newOption = $('<option/>');
                    newOption.attr('name', role.id);
                    newOption.attr('value', role.id);
                    newOption.html(role.authority);
                    if (user.roles.filter(function (r) {
                        return r.id === role.id;
                    }).length > 0) {
                        newOption.attr('selected', '');
                    }

                    modalbody.find('select[id="roles"]').append(newOption);
                });


                var modalfooter = $(event.currentTarget).find('.modal-footer');
                modalfooter.find('button[id="usereditbutton"]').attr('action', '/api/users/' + button.data('id'));
            });


            $("#buttonUserUpdate").click(function () {

                var modalbody = $('#userform');

                var data = {};
                data['id'] = modalbody.find('input[id="id"]').val();
                data['username'] = modalbody.find('input[id="username"]').val();
                data['firstName'] = modalbody.find('input[id="firstName"]').val();
                data['email'] = modalbody.find('input[id="email"]').val();
                data['password'] = modalbody.find('input[id="password"]').val();

                data['roles'] = [];

                modalbody.find('option:selected').each(function () {

                    data['roles'].push(parseInt(this.value));

                });

                console.log(JSON.stringify(data));


                $.ajax(
                    {
                        url: api_url + '/users/' + $('#userform').find('input[id="id"]').val(),
                        type: "PUT",
                        data: JSON.stringify(data),
                        contentType: "application/json",
                        dataType: 'json',

                    })
                    .done(function (msg) {
                        console.log("success: finish saving user: " + msg);
                    })
                    .fail(function () {
                        console.log("error: finish saving user");
                    });
            });
        }
    }


});


$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

