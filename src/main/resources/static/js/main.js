$(document).ready(function () {
        fire_ajax_submit();
});

function display(obj) {
    var id = $(obj).attr('data-id');
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/admin/edit/" + id,
        data: JSON,
        dataType: 'json',
        success: function (data) {

            $("#id1").attr("value", data.id);
            $("#name1").attr("value", data.name);
            $("#login1").attr("value", data.login);
            $("#password").attr("value", data.password);
            $("#modalEdit").css("display", "block");
        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);

        }

    });


}

function fire_ajax_submit() {
//todo передавать в дисплей сразу id
    //todo повесить fire_ajax_submit на onlick в 185 строке thumhome
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/admin/getall",
        data: JSON,
        dataType: 'json',
        success: function (data) {
            var temp = '';

            $.each(data, function (index, value) {

                temp += '<tr>';
                temp += '<td>' + value.id + '</td>';
                temp += '<td>' + value.name + '</td>';
                temp += '<td>' + value.login + '</td>';
                temp += '<td>' + '<button onclick="display(this)"' + ' class="btn btn-info show-modal editbutton"' + 'data-id=' + value.id + ' >Edit</button>' + '  ' + '<a class="btn btn-danger" href="/admin/delete/' + value.id + '"' + '  type="button">Delete</a>' + '</td>';
                temp += '</tr>';
            })

            $('#tablejs').append(temp);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }

    });

}