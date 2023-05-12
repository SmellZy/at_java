/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 94.76351351351352, "KoPercent": 5.236486486486487};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.941554054054054, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.0, 500, 1500, "GET_profile_page_55_a0d754ad-d"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_25_a430d523-c-1"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_25_a430d523-c-0"], "isController": false}, {"data": [0.0, 500, 1500, "GET_profile_page_<EOF>_<EOF>"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_25_already 2 deleted"], "isController": false}, {"data": [1.0, 500, 1500, "Post_auth"], "isController": false}, {"data": [1.0, 500, 1500, "GET_profile_page_<EOF>_already 2 deleted-0"], "isController": false}, {"data": [1.0, 500, 1500, "GET_profile_page_<EOF>_<EOF>-0"], "isController": false}, {"data": [1.0, 500, 1500, "GET_profile_page_<EOF>_already 2 deleted-1"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_55_ebda7555-4-1"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_55_ebda7555-4-0"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_<EOF>_<EOF>-0"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_<EOF>_already 2 deleted"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_25_already 2 deleted-1"], "isController": false}, {"data": [1.0, 500, 1500, "GET_profile_page_<EOF>_<EOF>-1"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_<EOF>_<EOF>-1"], "isController": false}, {"data": [1.0, 500, 1500, "Post_auth-1"], "isController": false}, {"data": [1.0, 500, 1500, "Post_auth-0"], "isController": false}, {"data": [0.0, 500, 1500, "GET_profile_page_55_ebda7555-4"], "isController": false}, {"data": [1.0, 500, 1500, "GET_bugtracker_DB_users_table"], "isController": false}, {"data": [0.55, 500, 1500, "GET_bugtracker_DB"], "isController": false}, {"data": [1.0, 500, 1500, "GET_phpmyadmin"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_55_ebda7555-4"], "isController": false}, {"data": [1.0, 500, 1500, "GET_profile_page_55_already 2 deleted-0"], "isController": false}, {"data": [1.0, 500, 1500, "GET_profile_page_55_already 2 deleted-1"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_25_already 2 deleted-0"], "isController": false}, {"data": [0.0, 500, 1500, "GET_profile_page_<EOF>_already 2 deleted"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_25_309da7fd-2-1"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_25_309da7fd-2-0"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_<EOF>_<EOF>"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_55_already 2 deleted-0"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_55_already 2 deleted-1"], "isController": false}, {"data": [1.0, 500, 1500, "GET_profile_page_55_ebda7555-4-0"], "isController": false}, {"data": [1.0, 500, 1500, "GET_profile_page_25_309da7fd-2-0"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_55_already 2 deleted"], "isController": false}, {"data": [1.0, 500, 1500, "GET_profile_page_55_ebda7555-4-1"], "isController": false}, {"data": [1.0, 500, 1500, "GET_profile_page_55_a0d754ad-d-0"], "isController": false}, {"data": [1.0, 500, 1500, "GET_profile_page_55_a0d754ad-d-1"], "isController": false}, {"data": [1.0, 500, 1500, "GET_profile_page_25_a430d523-c-0"], "isController": false}, {"data": [1.0, 500, 1500, "GET_profile_page_25_a430d523-c-1"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_<EOF>_already 2 deleted-0"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_<EOF>_already 2 deleted-1"], "isController": false}, {"data": [0.0, 500, 1500, "GET_profile_page_25_309da7fd-2"], "isController": false}, {"data": [1.0, 500, 1500, "GET_profile_page_25_309da7fd-2-1"], "isController": false}, {"data": [1.0, 500, 1500, "Post_pass-0"], "isController": false}, {"data": [1.0, 500, 1500, "Post_pass"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_55_a0d754ad-d"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_25_309da7fd-2"], "isController": false}, {"data": [1.0, 500, 1500, "Post_pass-1"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_25_a430d523-c"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_55_a0d754ad-d-1"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost_55_a0d754ad-d-0"], "isController": false}, {"data": [1.0, 500, 1500, "POST_delete_user_from_DB"], "isController": false}, {"data": [0.0, 500, 1500, "GET_profile_page_55_already 2 deleted"], "isController": false}, {"data": [0.0, 500, 1500, "GET_profile_page_25_a430d523-c"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 2960, 155, 5.236486486486487, 152.99966216216217, 49, 706, 114.0, 231.0, 264.0, 510.3899999999999, 97.93217535153019, 1119.9229735318445, 31.354263595947064], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["GET_profile_page_55_a0d754ad-d", 23, 23, 100.0, 211.1739130434783, 180, 252, 210.0, 236.6, 248.99999999999994, 252.0, 0.7839127471029311, 4.420925730657805, 0.32229225247102933], "isController": false}, {"data": ["GET_localhost_25_a430d523-c-1", 44, 0, 0.0, 107.45454545454545, 51, 162, 106.0, 122.5, 130.75, 162.0, 1.5021679014031613, 7.435957824075655, 0.28585732392202384], "isController": false}, {"data": ["GET_localhost_25_a430d523-c-0", 44, 0, 0.0, 109.54545454545456, 85, 186, 105.5, 137.0, 149.5, 186.0, 1.5018602587295629, 0.9901957751646926, 0.2621988812847732], "isController": false}, {"data": ["GET_profile_page_<EOF>_<EOF>", 32, 32, 100.0, 219.87500000000003, 189, 337, 213.0, 259.5, 299.9499999999999, 337.0, 1.1702750146284377, 6.599832515816998, 0.4811384581626682], "isController": false}, {"data": ["GET_localhost_25_already 2 deleted", 4, 0, 0.0, 220.25, 202, 243, 218.0, 243.0, 243.0, 243.0, 0.22737608003638016, 1.2746605346180082, 0.08393374829467941], "isController": false}, {"data": ["Post_auth", 309, 0, 0.0, 139.71521035598684, 52, 308, 114.0, 219.0, 229.5, 293.0999999999991, 10.35557491873052, 57.33811821357955, 3.931483073745769], "isController": false}, {"data": ["GET_profile_page_<EOF>_already 2 deleted-0", 19, 0, 0.0, 105.89473684210527, 89, 123, 105.0, 118.0, 123.0, 123.0, 0.6782807368270741, 0.46297122661716406, 0.13115193934742253], "isController": false}, {"data": ["GET_profile_page_<EOF>_<EOF>-0", 32, 0, 0.0, 107.84375, 91, 165, 101.5, 140.1, 163.04999999999998, 165.0, 1.1747430249632893, 0.8018639294236417, 0.22714757709251102], "isController": false}, {"data": ["GET_profile_page_<EOF>_already 2 deleted-1", 19, 0, 0.0, 114.5263157894737, 102, 144, 110.0, 136.0, 144.0, 144.0, 0.6785714285714286, 3.3636648995535716, 0.14777483258928573], "isController": false}, {"data": ["GET_localhost_55_ebda7555-4-1", 45, 0, 0.0, 107.80000000000001, 51, 153, 108.0, 122.0, 130.29999999999995, 153.0, 1.5104726100966703, 7.477003316746106, 0.2882944141883727], "isController": false}, {"data": ["GET_localhost_55_ebda7555-4-0", 45, 0, 0.0, 104.11111111111111, 74, 141, 104.0, 117.4, 123.59999999999997, 141.0, 1.5100671140939597, 0.9931444001677852, 0.26606412541946306], "isController": false}, {"data": ["GET_localhost_<EOF>_<EOF>-0", 88, 0, 0.0, 106.07954545454547, 55, 169, 103.0, 123.0, 132.79999999999995, 169.0, 3.0026956017333744, 1.972618263128263, 0.5289505280308459], "isController": false}, {"data": ["GET_localhost_<EOF>_already 2 deleted", 27, 0, 0.0, 217.66666666666669, 190, 281, 213.0, 246.59999999999997, 277.79999999999995, 281.0, 0.9605464441993667, 5.383965656017645, 0.35457671475328184], "isController": false}, {"data": ["GET_localhost_25_already 2 deleted-1", 4, 0, 0.0, 112.0, 99, 127, 111.0, 127.0, 127.0, 127.0, 0.22888532845044632, 1.1329711997882812, 0.043810082398718246], "isController": false}, {"data": ["GET_profile_page_<EOF>_<EOF>-1", 32, 0, 0.0, 111.93749999999999, 94, 172, 110.0, 128.2, 150.54999999999993, 172.0, 1.1752175988835434, 5.825518633625914, 0.2559311763193654], "isController": false}, {"data": ["GET_localhost_<EOF>_<EOF>-1", 88, 0, 0.0, 109.85227272727276, 60, 173, 108.0, 124.0, 132.2, 173.0, 2.9964587305911197, 14.832956204457233, 0.5718782986584038], "isController": false}, {"data": ["Post_auth-1", 88, 0, 0.0, 109.45454545454544, 89, 165, 106.0, 131.3, 142.84999999999997, 165.0, 2.988318391741375, 15.186694289510323, 0.6432816957008964], "isController": false}, {"data": ["Post_auth-0", 88, 0, 0.0, 105.86363636363635, 62, 151, 105.0, 120.10000000000001, 131.55, 151.0, 2.9922812744397977, 2.0342478599238327, 0.9480383343534292], "isController": false}, {"data": ["GET_profile_page_55_ebda7555-4", 18, 18, 100.0, 211.16666666666666, 194, 234, 210.0, 225.0, 234.0, 234.0, 0.6717921922818542, 3.788525999290886, 0.27619581342837946], "isController": false}, {"data": ["GET_bugtracker_DB_users_table", 39, 0, 0.0, 371.69230769230774, 337, 456, 364.0, 418.0, 436.0, 456.0, 1.4175117217315452, 178.74259866926542, 0.41943950359829896], "isController": false}, {"data": ["GET_bugtracker_DB", 40, 0, 0.0, 536.2249999999999, 416, 706, 522.0, 619.9999999999999, 677.5499999999998, 706.0, 1.3639308487059707, 353.3055410223514, 0.39159733351519077], "isController": false}, {"data": ["GET_phpmyadmin", 41, 0, 0.0, 276.17073170731715, 235, 469, 267.0, 328.8, 346.9, 469.0, 1.3801460935133132, 252.18951578121317, 0.3250825247416434], "isController": false}, {"data": ["GET_localhost_55_ebda7555-4", 45, 0, 0.0, 212.13333333333335, 159, 294, 212.0, 234.6, 249.1, 294.0, 1.5050167224080266, 8.439818927675585, 0.5524273620401338], "isController": false}, {"data": ["GET_profile_page_55_already 2 deleted-0", 5, 0, 0.0, 104.2, 90, 120, 107.0, 120.0, 120.0, 120.0, 0.2552843868069029, 0.1724166815582559, 0.04936162948024099], "isController": false}, {"data": ["GET_profile_page_55_already 2 deleted-1", 5, 0, 0.0, 116.0, 90, 165, 108.0, 165.0, 165.0, 165.0, 0.2555322737261716, 1.2666814662441865, 0.05564814164153933], "isController": false}, {"data": ["GET_localhost_25_already 2 deleted-0", 4, 0, 0.0, 107.75, 103, 116, 106.0, 116.0, 116.0, 116.0, 0.22866289372892012, 0.15000419810781457, 0.040641256502601045], "isController": false}, {"data": ["GET_profile_page_<EOF>_already 2 deleted", 19, 19, 100.0, 220.57894736842107, 197, 262, 220.0, 242.0, 262.0, 262.0, 0.6757958385203628, 3.811181475635782, 0.2778418437666726], "isController": false}, {"data": ["GET_localhost_25_309da7fd-2-1", 45, 0, 0.0, 108.73333333333336, 51, 144, 107.0, 123.0, 134.39999999999998, 144.0, 1.507689214996482, 7.463323365832411, 0.28694519130900925], "isController": false}, {"data": ["GET_localhost_25_309da7fd-2-0", 45, 0, 0.0, 107.84444444444446, 86, 163, 105.0, 133.79999999999998, 153.5999999999999, 163.0, 1.507537688442211, 0.993803653685092, 0.2632956448911223], "isController": false}, {"data": ["GET_localhost_<EOF>_<EOF>", 88, 0, 0.0, 216.11363636363637, 115, 313, 212.5, 236.20000000000002, 269.1, 313.0, 2.990857492437889, 16.77007039560888, 1.0976744383645447], "isController": false}, {"data": ["GET_localhost_55_already 2 deleted-0", 13, 0, 0.0, 99.84615384615385, 60, 136, 98.0, 127.6, 136.0, 136.0, 0.4423724776261612, 0.2903069384421683, 0.07862479582808725], "isController": false}, {"data": ["GET_localhost_55_already 2 deleted-1", 13, 0, 0.0, 101.92307692307692, 59, 114, 104.0, 113.6, 114.0, 114.0, 0.4415910866537586, 2.185895782380516, 0.08452329392982098], "isController": false}, {"data": ["GET_profile_page_55_ebda7555-4-0", 18, 0, 0.0, 102.77777777777777, 94, 119, 99.0, 115.4, 119.0, 119.0, 0.6746373824069563, 0.4604458697200255, 0.13044746261384504], "isController": false}, {"data": ["GET_profile_page_25_309da7fd-2-0", 30, 0, 0.0, 105.1, 52, 137, 103.0, 127.50000000000001, 134.8, 137.0, 1.0175010175010175, 0.694431195733279, 0.1967433608058608], "isController": false}, {"data": ["GET_localhost_55_already 2 deleted", 13, 0, 0.0, 201.84615384615384, 120, 238, 207.0, 231.2, 238.0, 238.0, 0.4406779661016949, 2.4705707097457625, 0.16267213983050846], "isController": false}, {"data": ["GET_profile_page_55_ebda7555-4-1", 18, 0, 0.0, 108.05555555555556, 97, 123, 109.0, 116.70000000000002, 123.0, 123.0, 0.6743846240305721, 3.3428724804241132, 0.14686305777228278], "isController": false}, {"data": ["GET_profile_page_55_a0d754ad-d-0", 23, 0, 0.0, 102.43478260869566, 86, 127, 101.0, 119.0, 125.39999999999998, 127.0, 0.7862979043451506, 0.5367070783904824, 0.1520380713479881], "isController": false}, {"data": ["GET_profile_page_55_a0d754ad-d-1", 23, 0, 0.0, 108.4782608695652, 89, 133, 104.0, 129.0, 132.6, 133.0, 0.7873476653430097, 3.9028735515370396, 0.17146340758934683], "isController": false}, {"data": ["GET_profile_page_25_a430d523-c-0", 28, 0, 0.0, 100.96428571428571, 54, 122, 103.0, 114.1, 118.84999999999998, 122.0, 0.9492168960607499, 0.6478855558003932, 0.18353998576174654], "isController": false}, {"data": ["GET_profile_page_25_a430d523-c-1", 28, 0, 0.0, 104.07142857142858, 51, 137, 104.0, 119.4, 130.69999999999996, 137.0, 0.9473862290644561, 4.693844104212485, 0.20631555574352903], "isController": false}, {"data": ["GET_localhost_<EOF>_already 2 deleted-0", 27, 0, 0.0, 104.70370370370372, 90, 129, 105.0, 116.6, 126.6, 129.0, 0.9640791259015925, 0.6313867452510177, 0.17135000089266586], "isController": false}, {"data": ["GET_localhost_<EOF>_already 2 deleted-1", 27, 0, 0.0, 112.8148148148148, 94, 175, 110.0, 131.0, 162.19999999999993, 175.0, 0.9638382179702281, 4.771187428604576, 0.18448465890836396], "isController": false}, {"data": ["GET_profile_page_25_309da7fd-2", 30, 30, 100.0, 214.60000000000002, 113, 279, 217.5, 238.3, 260.84999999999997, 279.0, 1.0140959334753068, 5.718880459216442, 0.4169281132745158], "isController": false}, {"data": ["GET_profile_page_25_309da7fd-2-1", 30, 0, 0.0, 109.36666666666669, 60, 141, 110.5, 125.50000000000001, 135.5, 141.0, 1.015916017609211, 5.035795165932949, 0.2212395233660684], "isController": false}, {"data": ["Post_pass-0", 307, 0, 0.0, 107.39087947882733, 49, 167, 106.0, 122.19999999999999, 134.59999999999997, 153.76000000000005, 10.30166772927083, 7.886657274504212, 3.4791657054293483], "isController": false}, {"data": ["Post_pass", 307, 0, 0.0, 218.2899022801304, 104, 327, 215.0, 242.0, 263.19999999999993, 296.0, 10.269619321602997, 60.12987425361277, 6.008142833846257], "isController": false}, {"data": ["GET_localhost_55_a0d754ad-d", 44, 0, 0.0, 216.43181818181816, 117, 318, 212.0, 244.0, 283.5, 318.0, 1.5007845009891534, 8.417297234207654, 0.5476051401869159], "isController": false}, {"data": ["GET_localhost_25_309da7fd-2", 45, 0, 0.0, 216.75555555555556, 159, 285, 213.0, 247.6, 281.4, 285.0, 1.5022533800701052, 8.426735154815557, 0.5482833625438157], "isController": false}, {"data": ["Post_pass-1", 307, 0, 0.0, 110.66775244299677, 53, 182, 109.0, 126.19999999999999, 134.19999999999993, 172.4400000000001, 10.287169520490568, 52.3570749421975, 2.5441411616627017], "isController": false}, {"data": ["GET_localhost_25_a430d523-c", 44, 0, 0.0, 217.2954545454545, 159, 300, 213.0, 250.0, 294.25, 300.0, 1.4966495459029219, 8.395401416714854, 0.5460963808292799], "isController": false}, {"data": ["GET_localhost_55_a0d754ad-d-1", 44, 0, 0.0, 107.74999999999997, 53, 157, 107.0, 122.0, 137.5, 157.0, 1.5040678197853286, 7.445362706809325, 0.28621887177821836], "isController": false}, {"data": ["GET_localhost_55_a0d754ad-d-0", 44, 0, 0.0, 108.49999999999999, 63, 163, 106.0, 127.5, 156.0, 163.0, 1.5067461132799123, 0.9921128582802548, 0.2630518671666324], "isController": false}, {"data": ["POST_delete_user_from_DB", 39, 0, 0.0, 249.05128205128207, 227, 290, 243.0, 276.0, 284.0, 290.0, 1.4244493955221154, 1.4912561292413893, 1.352114074655758], "isController": false}, {"data": ["GET_profile_page_55_already 2 deleted", 5, 5, 100.0, 220.4, 196, 285, 198.0, 285.0, 285.0, 285.0, 0.2541166903842244, 1.4312924057227079, 0.1044757096208579], "isController": false}, {"data": ["GET_profile_page_25_a430d523-c", 28, 28, 100.0, 205.17857142857144, 105, 233, 208.5, 226.2, 230.75, 233.0, 0.9456584146712148, 5.330740336553075, 0.3887912036880678], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["User not found", 155, 100.0, 5.236486486486487], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 2960, 155, "User not found", 155, "", "", "", "", "", "", "", ""], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": ["GET_profile_page_55_a0d754ad-d", 23, 23, "User not found", 23, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["GET_profile_page_<EOF>_<EOF>", 32, 32, "User not found", 32, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["GET_profile_page_55_ebda7555-4", 18, 18, "User not found", 18, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["GET_profile_page_<EOF>_already 2 deleted", 19, 19, "User not found", 19, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["GET_profile_page_25_309da7fd-2", 30, 30, "User not found", 30, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["GET_profile_page_55_already 2 deleted", 5, 5, "User not found", 5, "", "", "", "", "", "", "", ""], "isController": false}, {"data": ["GET_profile_page_25_a430d523-c", 28, 28, "User not found", 28, "", "", "", "", "", "", "", ""], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
