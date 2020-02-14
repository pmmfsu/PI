var pat = 1;
var graf = 1;
var year = 2020;

function changeGraf() {
    if (graf === 1) {
        google.charts.load('current', {'packages': ['corechart']});
        google.charts.setOnLoadCallback(drawChart);
    }
}

function drawChart() {
    firebase.initializeApp({
        apiKey: 'AIzaSyD3s7OmnuPbyPBDi9CxEBzqLLItGBPrij8',
        authDomain: 'projetointegrado-71528.firebaseapp.com',
        databaseURL: "https://projetointegrado-71528.firebaseio.com",
        projectId: 'projetointegrado-71528'
    });

    var db = firebase.firestore();
    var ref = db.collection("Twitter");
    ref.where("timestamp", ">=", "2020-02").where("timestamp", "<", "2020-03").get()
        .then(function(querySnapshot) {
            querySnapshot.forEach(function(doc) {
                // doc.data() is never undefined for query doc snapshots
                console.log(doc.id, " => ", doc.data());
            });
        });



    var data = google.visualization.arrayToDataTable([
        ['Year', 'Sales', 'Expenses'],
        ['2004', 1000, 400],
        ['2005', 1170, 460],
        ['2006', 660, 1120],
        ['2007', 1030, 540]
    ]);

    var options = {
        title: 'Total Comments in Year:'.year,
        curveType: 'function',
        legend: {position: 'bottom'}
    };

    var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

    chart.draw(data, options);
}

$(document).ready(function () {

    for (let i = new Date().getFullYear(); i > 2010; i--) {
        $('#yearpicker').append($('<option />').val(i).html(i));
    }
    changeGraf();
    $("#pat").change(function () {
        pat = this.value;
        graf = 1;
        $("#graph").val(1);
        changeGraf()
    });
    $("#graph").change(function () {
        graf = this.value;
        changeGraf()
    });
    $("#yearpicker").change(function () {
        year = this.value;
        changeGraf()
    });

});