var pat = 1;
var graf = 1;
var year = 2020;
var db;
const firebaseConfig = {
    apiKey: "AIzaSyD3s7OmnuPbyPBDi9CxEBzqLLItGBPrij8",
    authDomain: "projetointegrado-71528.firebaseapp.com",
    databaseURL: "https://projetointegrado-71528.firebaseio.com",
    projectId: "projetointegrado-71528",
    storageBucket: "projetointegrado-71528.appspot.com",
    messagingSenderId: "766880522684",
    appId: "1:766880522684:web:dd05603ac846464e048bb9",
    measurementId: "G-5S4WZFHCTW"
};
function changeGraf() {
    if (graf === 1) {
        google.charts.load('current', {'packages': ['corechart']});
        google.charts.setOnLoadCallback(drawChart);
    }
}

function drawChart() {
    var options = {
        title: 'Total Comments in Year:'+year,
        curveType: 'function',
        legend: {position: 'bottom'}
    };

    var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
    var dataArray = [
        {label: 'Month', type: 'number'},
        {label: 'Comments', type: 'number'},
    ];
  //  var dataArray = [['Month', 'Comments']];
    var ref = db.collection("Twitter");
    for (let i = 1; i < 13; i++) {
        let start;
        let end;
        if (i < 10){
             start = new Date(year+'-0'+i+'-01');
             end = new Date(year+'-0'+i+'-01');
        }else{
             start = new Date(year+'-'+i+'-01');
             end = new Date(year+'-'+i+'-01');
        }
        end = new Date(end.setMonth(end.getMonth()+1));
        ref.where("date", ">=", start).where("date", "<", end).where("Patrimony_Id", "==", pat).get()
            .then(function(querySnapshot) {
                dataArray.push([i.toString(), querySnapshot.size]);
                console.log([i, querySnapshot.size]);
                var data = google.visualization.arrayToDataTable([
                    dataArray
                ]);
                console.log(data.getColumnType(1));
                chart.draw(data, options);
            });

    }
}

$(document).ready(function () {
    firebase.initializeApp(firebaseConfig);
    db = firebase.firestore();
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