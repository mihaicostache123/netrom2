function makeTable(container, data) {
    var table = $("<table/>").addClass('teamTable');

    var headerRow = $("<tr/>");
    headerRow.append($("<th/>").text("ID"));
    headerRow.append($("<th/>").text("Name"));
    headerRow.append($("<th/>").text("Country"));
    table.append(headerRow);
    $.each(data, function (rowIndex, r) {

        var row = $("<tr/>");
        $.each(r, function (colIndex, c) {
            if(colIndex == "id" && c != ""){
                row.append($("<td/>").text(c));
            }
            if(colIndex == "country" && c != ""){
                row.append($("<td/>").text(c));
            }
            if(colIndex == "name" && c != ""){
                row.append($("<td/>").text(c));
            }
        });
        table.append(row);
    });
    return container.append(table);
}

$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/team/all",
        type: "GET",
        dataType: "json",
        success: function (data) {
            var teamTableContainer = $("#teamTableContainer");
            makeTable(teamTableContainer, data);
        },
        error: function (data) {
            alert('Error: ' + data);
        }
    });
});

function showForm() {
    var form = document.getElementById("addForm");
    form.style.display = "block";
}

function createTeam() {
    var name = document.getElementById("name").value;
    var country = document.getElementById("country").value;

    var data = {
        name: name,
        country: country
    };
    if(data.name != "" && data.country != ""){
    $.ajax({
        url: "http://localhost:8080/team/add",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            window.location.reload();
        },
        error: function (error) {
            alert('Error: ' + data);
        }
    });
    }
    else
        alert("Please fill in all fields!");
};

function deleteTeam(){
    var id = $("#deleteId").val();
    var data = {
        id: id
    };
    if(data.id != ""){
    $.ajax({
        url: "http://localhost:8080/team/delete/" + id,
        type: "DELETE",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            window.location.reload();
        },
        error: function (error) {
            alert('Error: ' + data);
        }
    });
    }
    else
        alert("Please fill in all fields!");
}