function makeTable(container, data) {
    var table = $("<table/>").addClass('playerTable');

    var headerRow = $("<tr/>");
    headerRow.append($("<th>").text("ID"));
    headerRow.append($("<th/>").text("Name"));
    headerRow.append($("<th/>").text("Age"));
    headerRow.append($("<th/>").text("Position"));
    headerRow.append($("<th/>").text("Number"));
    headerRow.append($("<th/>").text("Signing Date"));
    headerRow.append($("<th/>").text("Team"));
    table.append(headerRow);
    $.each(data, function (rowIndex, r) {

        var row = $("<tr/>");
        $.each(r, function (colIndex, c) {
            if(colIndex == "position"){
               if(c == 1){
                     row.append($("<td/>").text("Forward"));
               }
               else if(c == 2){
                     row.append($("<td/>").text("Midfielder"));
               }
               else if(c == 3){
                     row.append($("<td/>").text("Defender"));
               }
               else if(c == 4){
                     row.append($("<td/>").text("Goalkeeper"));
               }
            }
            else if (colIndex == "team") {
                row.append($("<td/>").text(c.name));
            }
            else{
                row.append($("<td/>").text(c));
            }

        });
        table.append(row);
    });
    return container.append(table);
}

$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/player/all",
        type: "GET",
        dataType: "json",
        success: function (data) {
            var playerTableContainer = $("#playerTableContainer");
            makeTable(playerTableContainer, data);
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

function createPlayer() {
        var name = $('#name').val();
        var age = $('#age').val();
        var position = $('#position').val();
        var number = $('#number').val();
        var date = $('#date').val();
        var teamIdValue = $('#team').val();
        teamId = Number(teamIdValue);
        var data = {
            name: name,
            age: age,
            position: position,
            number: number,
            signingDate: date,
            team: {
                id: teamId
            }

        };
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/player/add',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function(response) {
                console.log('Player added successfully');
                window.location.reload();
            },
            error: function(error) {
                console.log('Error:', error);
            }
        });
};

function deletePlayer() {
    var id = $('#deleteId').val();
    id = Number(id);
    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/player/delete/' + id,
        success: function(response) {
            window.location.reload();
        },
        error: function(error) {
            console.log('Error:', error);
        }
    });
}

