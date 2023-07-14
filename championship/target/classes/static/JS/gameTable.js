function makeTable(container, data) {
    var table = $("<table/>").addClass('gameTable');

    var headerRow = $("<tr/>");
    headerRow.append($("<th/>").text("ID"));
    headerRow.append($("<th/>").text("Score1"));
    headerRow.append($("<th/>").text("Score2"));
    headerRow.append($("<th/>").text("Date"));
    headerRow.append($("<th/>").text("Team1"));
    headerRow.append($("<th/>").text("Team2"));
    table.append(headerRow);
    $.each(data, function (rowIndex, r) {

        var row = $("<tr/>");
        $.each(r, function (colIndex, c) {
            if(colIndex == "team1" || colIndex == "team2"){
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
        url: "http://localhost:8080/game/all",
        type: "GET",
        dataType: "json",
        success: function (data) {
            var gameTableContainer = $("#gameTableContainer");
            makeTable(gameTableContainer, data);
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

function createGame(){
    var date = $("#date").val();
    var score1 = $("#score1").val();
    var score2 = $("#score2").val();
    var team1IdValue = $("#team1").val();
    var team2IdValue = $("#team2").val();
    team1Id = Number(team1IdValue);
    team2Id = Number(team2IdValue);
    var data = {
        date: date,
        score1: score1,
        score2: score2,
        team1: {
            id: team1Id
        },
        team2: {
            id: team2Id
        }
    };
    $.ajax({
        url: "http://localhost:8080/game/add",
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
};

function deleteGame(){
    var id = $("#deleteId").val();
    var data = {
        id: id
    };
    $.ajax({
        url: "http://localhost:8080/game/delete/" + id,
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