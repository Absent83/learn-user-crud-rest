var api_url = 'http://localhost:8080/api';


$(document).ready(function () {
    console.log("start loading users and roles");


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
            makeListTab();
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
            makeListTab();
            makeNewUserTab();
        }
    });


    function makeListTab() {

        if (isRolesReady && isUsersReady) {

            $.each(usersJsonFromServer, function (i, user) {

                var tdROLES = $('<div/>');

                $.each(user.roles, function (k, role) {
                    tdROLES.append($('<p/>').html(role.authority));
                });


                var buttonUserEdit = $('<button/>');
                buttonUserEdit.attr("class", "btn btn-primary");
                buttonUserEdit.attr("id", "buttonUserEdit" + user.id);
                buttonUserEdit.attr("data-target", "#usereditmodaljs");
                buttonUserEdit.attr("data-toggle", "modal");
                buttonUserEdit.attr("type", "button");
                buttonUserEdit.html("Edit");
                buttonUserEdit.data('user', user);


                var buttonUserDelete = $('<button/>');
                buttonUserDelete.attr("class", "btn btn-danger");
                buttonUserDelete.attr("type", "button");
                buttonUserDelete.html("Delete");
                buttonUserDelete.click(function () {
                    deleteUser(user.id)
                });


                var newTr = $('<tr/>');
                newTr.append($('<td/>').html(user.id));
                newTr.append($('<td/>').html(user.username));
                newTr.append($('<td/>').html(user.firstName));
                newTr.append($('<td/>').html(user.email));
                newTr.append($('<td/>').html(tdROLES));
                newTr.append($('<td/>').html(user.password));
                newTr.append($('<td/>').append(buttonUserEdit));
                newTr.append($('<td/>').append(buttonUserDelete));


                $('#usertable').append(newTr);
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

                var jsonData = JSON.stringify(getData($('#userform')));

                console.log(jsonData);
                UpdateUser(jsonData);

            });
        }
    }


    function makeNewUserTab() {
        if (isRolesReady) {

            $("#adduserjs").find('select[id="roles"]').html('');

            $.each(rolesJsonFromServer, function (i, role) {

                var newOption = $('<option/>');
                newOption.attr('name', role.id);
                newOption.attr('value', role.id);
                newOption.html(role.authority);

                $("#adduserjs").find('select[id="roles"]').append(newOption);
            });

            $("#buttonUserAdd").click(function () {

                var data = getData($('#adduserjs'));
                data['id'] = "0";

                var jsonData = JSON.stringify(data);

                console.log(jsonData);
                AddUser(jsonData);

            });


        }
    }

});


function AddUser(jsonToSend) {

    console.log(jsonToSend);

    $.ajax(
        {
            url: api_url + '/users',
            type: "POST",
            data: jsonToSend,
            contentType: "application/json",
            dataType: 'json',

        })
        .done(function (msg) {
            console.log("success: finish add user: " + msg);
        })
        .fail(function () {
            console.log("error: finish add user");
        });

}


function UpdateUser(jsonToSend) {

    console.log(jsonToSend);

    $.ajax(
        {
            url: api_url + '/users/' + JSON.parse(jsonToSend).id,
            type: "PUT",
            data: jsonToSend,
            contentType: "application/json",
            dataType: 'json',

        })
        .done(function (msg) {
            console.log("success: finish saving user: " + msg);
        })
        .fail(function () {
            console.log("error: finish saving user");
        });

}

function deleteUser(id) {
    $.ajax(
        {
            url: api_url + '/users/' + id,
            type: "DELETE",
        })
        .done(function (msg) {
            console.log("success: finish deleting user: " + msg);
        })
        .fail(function () {
            console.log("error: finish deleting user");
        });
}


function getData(obj) {

    var data = {};
    data['id'] = obj.find('input[id="id"]').val();
    data['username'] = obj.find('input[id="username"]').val();
    data['firstName'] = obj.find('input[id="firstName"]').val();
    data['email'] = obj.find('input[id="email"]').val();
    data['password'] = obj.find('input[id="password"]').val();

    data['roles'] = [];

    obj.find('option:selected').each(function () {

        data['roles'].push(parseInt(this.value));

    });

    return data;
}