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

    var data = {"OkPercent": 100.0, "KoPercent": 0.0};
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
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [1.0, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [1.0, 500, 1500, "GET_issue_page_161"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_121"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_163"], "isController": false}, {"data": [1.0, 500, 1500, "GET_myIssues_page"], "isController": false}, {"data": [1.0, 500, 1500, "GET_home_page_and_chek_if_user_close_issue"], "isController": false}, {"data": [1.0, 500, 1500, "Post_auth"], "isController": false}, {"data": [1.0, 500, 1500, "Post_auth-1"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_169"], "isController": false}, {"data": [1.0, 500, 1500, "Post_auth-0"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_124"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_168"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_167"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_122"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_98"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_129"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_99"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_127"], "isController": false}, {"data": [1.0, 500, 1500, "Post_auth-2"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_126"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_94"], "isController": false}, {"data": [1.0, 500, 1500, "GET_createissue_page"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_172"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_95"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_96"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_170"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_132"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_131"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_175"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_173"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_136"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_135"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_134"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_178"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_177"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_138"], "isController": false}, {"data": [1.0, 500, 1500, "CLOSE_Issue"], "isController": false}, {"data": [1.0, 500, 1500, "POST_issue"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_180"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_143"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_141"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_140"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_109"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_103"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_147"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_146"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_101"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_144"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_107"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_106"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_149"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_150"], "isController": false}, {"data": [1.0, 500, 1500, "Post_pass-0"], "isController": false}, {"data": [1.0, 500, 1500, "Post_pass"], "isController": false}, {"data": [1.0, 500, 1500, "Post_pass-2"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_154"], "isController": false}, {"data": [1.0, 500, 1500, "Post_pass-1"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_153"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_152"], "isController": false}, {"data": [1.0, 500, 1500, "Post_pass-3"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_119"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_114"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_158"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_112"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_156"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost-1"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_117"], "isController": false}, {"data": [1.0, 500, 1500, "GET_localhost-0"], "isController": false}, {"data": [1.0, 500, 1500, "POST_close_issue"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_115"], "isController": false}, {"data": [1.0, 500, 1500, "GET_issue_page_159"], "isController": false}]}, function(index, item){
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
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 3503, 0, 0.0, 62.695118469883106, 28, 215, 52.0, 140.0, 146.0, 192.0, 19.476042743881422, 538.1517865954177, 10.748366282205247], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["GET_issue_page_161", 1, 0, 0.0, 76.0, 76, 76, 76.0, 76.0, 76.0, 76.0, 13.157894736842104, 328.58758223684214, 7.786800986842105], "isController": false}, {"data": ["GET_issue_page_121", 1, 0, 0.0, 41.0, 41, 41, 41.0, 41.0, 41.0, 41.0, 24.390243902439025, 609.0891768292682, 13.767149390243901], "isController": false}, {"data": ["GET_issue_page_163", 2, 0, 0.0, 39.5, 39, 40, 39.5, 40.0, 40.0, 40.0, 1.0309278350515465, 25.74500644329897, 0.6100998711340206], "isController": false}, {"data": ["GET_myIssues_page", 55, 0, 0.0, 44.83636363636365, 37, 94, 40.0, 67.19999999999999, 86.79999999999998, 94.0, 0.3299340131973605, 25.032343140746853, 0.19677705083983205], "isController": false}, {"data": ["GET_home_page_and_chek_if_user_close_issue", 108, 0, 0.0, 64.76851851851848, 51, 112, 56.0, 99.40000000000003, 108.55, 111.82, 0.6554153694904146, 33.99358585220687, 0.18241541045387513], "isController": false}, {"data": ["Post_auth", 251, 0, 0.0, 121.93227091633462, 33, 182, 114.0, 158.0, 170.0, 176.0, 1.4070374294379138, 80.7126451007349, 1.6007306189002684], "isController": false}, {"data": ["Post_auth-1", 248, 0, 0.0, 29.689516129032246, 28, 39, 29.0, 33.0, 33.0, 37.50999999999999, 1.4683505331651834, 0.9659467474999557, 0.49395050126704443], "isController": false}, {"data": ["GET_issue_page_169", 1, 0, 0.0, 53.0, 53, 53, 53.0, 53.0, 53.0, 53.0, 18.867924528301884, 471.2006191037736, 11.165978773584905], "isController": false}, {"data": ["Post_auth-0", 248, 0, 0.0, 39.11693548387097, 30, 88, 31.5, 67.1, 82.0, 87.0, 1.4678899082568808, 8.023910319251259, 0.687316190071027], "isController": false}, {"data": ["GET_issue_page_124", 1, 0, 0.0, 40.0, 40, 40, 40.0, 40.0, 40.0, 40.0, 25.0, 624.31640625, 14.1845703125], "isController": false}, {"data": ["GET_issue_page_168", 1, 0, 0.0, 75.0, 75, 75, 75.0, 75.0, 75.0, 75.0, 13.333333333333334, 332.96875, 7.890625], "isController": false}, {"data": ["GET_issue_page_167", 1, 0, 0.0, 92.0, 92, 92, 92.0, 92.0, 92.0, 92.0, 10.869565217391305, 271.44191576086956, 6.43257472826087], "isController": false}, {"data": ["GET_issue_page_122", 1, 0, 0.0, 41.0, 41, 41, 41.0, 41.0, 41.0, 41.0, 24.390243902439025, 609.0891768292682, 13.790967987804878], "isController": false}, {"data": ["GET_issue_page_98", 1, 0, 0.0, 73.0, 73, 73, 73.0, 73.0, 73.0, 73.0, 13.698630136986301, 341.8503852739726, 7.42455051369863], "isController": false}, {"data": ["GET_issue_page_129", 1, 0, 0.0, 40.0, 40, 40, 40.0, 40.0, 40.0, 40.0, 25.0, 624.31640625, 14.306640625], "isController": false}, {"data": ["GET_issue_page_99", 1, 0, 0.0, 39.0, 39, 39, 39.0, 39.0, 39.0, 39.0, 25.64102564102564, 639.8737980769231, 13.897235576923077], "isController": false}, {"data": ["GET_issue_page_127", 1, 0, 0.0, 41.0, 41, 41, 41.0, 41.0, 41.0, 41.0, 24.390243902439025, 609.0891768292682, 13.910060975609756], "isController": false}, {"data": ["Post_auth-2", 248, 0, 0.0, 53.48387096774191, 51, 66, 53.0, 56.099999999999994, 59.0, 63.0, 1.4681766784870676, 76.1506113209446, 0.5039283808764068], "isController": false}, {"data": ["GET_issue_page_126", 1, 0, 0.0, 70.0, 70, 70, 70.0, 70.0, 70.0, 70.0, 14.285714285714285, 356.7522321428571, 8.133370535714285], "isController": false}, {"data": ["GET_issue_page_94", 1, 0, 0.0, 104.0, 104, 104, 104.0, 104.0, 104.0, 104.0, 9.615384615384617, 239.95267427884616, 5.211463341346154], "isController": false}, {"data": ["GET_createissue_page", 87, 0, 0.0, 43.52873563218391, 31, 87, 34.0, 66.20000000000002, 81.0, 87.0, 0.530636641984947, 9.128638263247618, 0.14924155555826635], "isController": false}, {"data": ["GET_issue_page_172", 1, 0, 0.0, 39.0, 39, 39, 39.0, 39.0, 39.0, 39.0, 25.64102564102564, 640.3245192307693, 15.174278846153847], "isController": false}, {"data": ["GET_issue_page_95", 1, 0, 0.0, 87.0, 87, 87, 87.0, 87.0, 87.0, 87.0, 11.494252873563218, 286.8399784482759, 6.22979525862069], "isController": false}, {"data": ["GET_issue_page_96", 1, 0, 0.0, 86.0, 86, 86, 86.0, 86.0, 86.0, 86.0, 11.627906976744185, 290.17532703488376, 6.302234738372094], "isController": false}, {"data": ["GET_issue_page_170", 1, 0, 0.0, 42.0, 42, 42, 42.0, 42.0, 42.0, 42.0, 23.809523809523807, 594.5870535714286, 14.090401785714285], "isController": false}, {"data": ["GET_issue_page_132", 1, 0, 0.0, 69.0, 69, 69, 69.0, 69.0, 69.0, 69.0, 14.492753623188406, 361.90840126811594, 8.336163949275361], "isController": false}, {"data": ["GET_issue_page_131", 1, 0, 0.0, 40.0, 40, 40, 40.0, 40.0, 40.0, 40.0, 25.0, 624.31640625, 14.35546875], "isController": false}, {"data": ["GET_issue_page_175", 2, 0, 0.0, 89.0, 81, 97, 89.0, 97.0, 97.0, 97.0, 0.6514657980456027, 16.268831433224758, 0.38553542345276876], "isController": false}, {"data": ["GET_issue_page_173", 1, 0, 0.0, 91.0, 91, 91, 91.0, 91.0, 91.0, 91.0, 10.989010989010989, 274.42479395604397, 6.503262362637363], "isController": false}, {"data": ["GET_issue_page_136", 1, 0, 0.0, 40.0, 40, 40, 40.0, 40.0, 40.0, 40.0, 25.0, 624.31640625, 14.4775390625], "isController": false}, {"data": ["GET_issue_page_135", 1, 0, 0.0, 63.0, 63, 63, 63.0, 63.0, 63.0, 63.0, 15.873015873015872, 396.39136904761904, 9.176587301587302], "isController": false}, {"data": ["GET_issue_page_134", 1, 0, 0.0, 39.0, 39, 39, 39.0, 39.0, 39.0, 39.0, 25.64102564102564, 640.3245192307693, 14.798677884615385], "isController": false}, {"data": ["GET_issue_page_178", 1, 0, 0.0, 90.0, 90, 90, 90.0, 90.0, 90.0, 90.0, 11.11111111111111, 277.4631076388889, 6.575520833333334], "isController": false}, {"data": ["GET_issue_page_177", 1, 0, 0.0, 90.0, 90, 90, 90.0, 90.0, 90.0, 90.0, 11.11111111111111, 277.47395833333337, 6.575520833333334], "isController": false}, {"data": ["GET_issue_page_138", 1, 0, 0.0, 40.0, 40, 40, 40.0, 40.0, 40.0, 40.0, 25.0, 624.31640625, 14.5263671875], "isController": false}, {"data": ["CLOSE_Issue", 55, 0, 0.0, 44.345454545454544, 31, 89, 33.0, 78.4, 87.0, 89.0, 0.32219703227242635, 2.8398819422476462, 0.271378917256873], "isController": false}, {"data": ["POST_issue", 87, 0, 0.0, 146.3103448275862, 105, 215, 140.0, 203.0, 208.6, 215.0, 0.5262202866993286, 4.625160190981672, 0.3453320631464344], "isController": false}, {"data": ["GET_issue_page_180", 1, 0, 0.0, 91.0, 91, 91, 91.0, 91.0, 91.0, 91.0, 10.989010989010989, 274.42479395604397, 6.503262362637363], "isController": false}, {"data": ["GET_issue_page_143", 1, 0, 0.0, 41.0, 41, 41, 41.0, 41.0, 41.0, 41.0, 24.390243902439025, 609.0891768292682, 14.291158536585366], "isController": false}, {"data": ["GET_issue_page_141", 1, 0, 0.0, 41.0, 41, 41, 41.0, 41.0, 41.0, 41.0, 24.390243902439025, 609.0891768292682, 14.243521341463413], "isController": false}, {"data": ["GET_issue_page_140", 1, 0, 0.0, 41.0, 41, 41, 41.0, 41.0, 41.0, 41.0, 24.390243902439025, 609.0891768292682, 14.219702743902438], "isController": false}, {"data": ["GET_issue_page_109", 1, 0, 0.0, 40.0, 40, 40, 40.0, 40.0, 40.0, 40.0, 25.0, 624.31640625, 13.818359375], "isController": false}, {"data": ["GET_issue_page_103", 1, 0, 0.0, 63.0, 63, 63, 63.0, 63.0, 63.0, 63.0, 15.873015873015872, 396.34486607142856, 8.680555555555555], "isController": false}, {"data": ["GET_issue_page_147", 1, 0, 0.0, 40.0, 40, 40, 40.0, 40.0, 40.0, 40.0, 25.0, 624.31640625, 14.74609375], "isController": false}, {"data": ["GET_issue_page_146", 1, 0, 0.0, 39.0, 39, 39, 39.0, 39.0, 39.0, 39.0, 25.64102564102564, 640.3245192307693, 15.099158653846153], "isController": false}, {"data": ["GET_issue_page_101", 1, 0, 0.0, 71.0, 71, 71, 71.0, 71.0, 71.0, 71.0, 14.084507042253522, 351.67253521126764, 7.674955985915494], "isController": false}, {"data": ["GET_issue_page_144", 1, 0, 0.0, 39.0, 39, 39, 39.0, 39.0, 39.0, 39.0, 25.64102564102564, 640.2994791666666, 15.049078525641026], "isController": false}, {"data": ["GET_issue_page_107", 1, 0, 0.0, 88.0, 88, 88, 88.0, 88.0, 88.0, 88.0, 11.363636363636363, 283.76908735795456, 6.258877840909091], "isController": false}, {"data": ["GET_issue_page_106", 1, 0, 0.0, 88.0, 88, 88, 88.0, 88.0, 88.0, 88.0, 11.363636363636363, 283.7468927556818, 6.2477805397727275], "isController": false}, {"data": ["GET_issue_page_149", 1, 0, 0.0, 44.0, 44, 44, 44.0, 44.0, 44.0, 44.0, 22.727272727272727, 567.5603693181819, 13.449928977272728], "isController": false}, {"data": ["GET_issue_page_150", 1, 0, 0.0, 41.0, 41, 41, 41.0, 41.0, 41.0, 41.0, 24.390243902439025, 609.0891768292682, 14.434070121951219], "isController": false}, {"data": ["Post_pass-0", 250, 0, 0.0, 37.72400000000002, 29, 88, 31.0, 71.70000000000002, 79.0, 84.98000000000002, 1.4754659521476883, 1.2499559665629907, 0.7140967031451033], "isController": false}, {"data": ["Post_pass", 250, 0, 0.0, 151.86799999999997, 140, 205, 145.0, 191.9, 198.89999999999998, 205.0, 1.474508693703258, 79.65608144522496, 2.2512407069089577], "isController": false}, {"data": ["Post_pass-2", 250, 0, 0.0, 28.96799999999999, 28, 32, 29.0, 30.0, 30.0, 31.49000000000001, 1.4759886171757843, 0.9709814180413041, 0.49575344237445246], "isController": false}, {"data": ["GET_issue_page_154", 1, 0, 0.0, 41.0, 41, 41, 41.0, 41.0, 41.0, 41.0, 24.390243902439025, 609.0891768292682, 14.434070121951219], "isController": false}, {"data": ["Post_pass-1", 250, 0, 0.0, 29.575999999999983, 28, 36, 29.0, 32.0, 34.0, 35.49000000000001, 1.4759450476139873, 0.9606557328657539, 0.5375380332589457], "isController": false}, {"data": ["GET_issue_page_153", 1, 0, 0.0, 39.0, 39, 39, 39.0, 39.0, 39.0, 39.0, 25.64102564102564, 640.3245192307693, 15.174278846153847], "isController": false}, {"data": ["GET_issue_page_152", 1, 0, 0.0, 41.0, 41, 41, 41.0, 41.0, 41.0, 41.0, 24.390243902439025, 609.0891768292682, 14.434070121951219], "isController": false}, {"data": ["Post_pass-3", 250, 0, 0.0, 55.03200000000002, 52, 61, 55.0, 57.0, 58.0, 59.98000000000002, 1.4757882184874942, 76.54357186313835, 0.5057745287070324], "isController": false}, {"data": ["GET_issue_page_119", 1, 0, 0.0, 46.0, 46, 46, 46.0, 46.0, 46.0, 46.0, 21.73913043478261, 542.8838315217391, 12.228260869565217], "isController": false}, {"data": ["GET_issue_page_114", 1, 0, 0.0, 38.0, 38, 38, 38.0, 38.0, 38.0, 38.0, 26.31578947368421, 657.1751644736843, 14.674136513157896], "isController": false}, {"data": ["GET_issue_page_158", 1, 0, 0.0, 77.0, 77, 77, 77.0, 77.0, 77.0, 77.0, 12.987012987012989, 324.320211038961, 7.685673701298701], "isController": false}, {"data": ["GET_issue_page_112", 1, 0, 0.0, 52.0, 52, 52, 52.0, 52.0, 52.0, 52.0, 19.230769230769234, 480.24338942307696, 10.685847355769232], "isController": false}, {"data": ["GET_issue_page_156", 1, 0, 0.0, 60.0, 60, 60, 60.0, 60.0, 60.0, 60.0, 16.666666666666668, 416.19466145833337, 9.86328125], "isController": false}, {"data": ["GET_localhost", 252, 0, 0.0, 96.6507936507937, 80, 149, 85.0, 136.70000000000002, 141.35, 146.94, 1.4010741568535878, 72.81037315240296, 0.9360800745849596], "isController": false}, {"data": ["GET_localhost-1", 252, 0, 0.0, 54.50793650793649, 36, 70, 53.5, 60.0, 61.0, 64.0, 1.4017677849287713, 71.92317924896953, 0.4795815851796432], "isController": false}, {"data": ["GET_issue_page_117", 1, 0, 0.0, 40.0, 40, 40, 40.0, 40.0, 40.0, 40.0, 25.0, 624.31640625, 14.013671875], "isController": false}, {"data": ["GET_localhost-0", 252, 0, 0.0, 41.96825396825394, 28, 87, 31.0, 77.0, 83.35, 86.47, 1.4015339009916408, 0.9230860041962593, 0.456885669663465], "isController": false}, {"data": ["POST_close_issue", 55, 0, 0.0, 48.56363636363637, 38, 92, 41.0, 75.6, 85.39999999999998, 92.0, 0.3228495286396882, 5.378551619970885, 0.23756268050223647], "isController": false}, {"data": ["GET_issue_page_115", 1, 0, 0.0, 41.0, 41, 41, 41.0, 41.0, 41.0, 41.0, 24.390243902439025, 609.0891768292682, 13.624237804878048], "isController": false}, {"data": ["GET_issue_page_159", 1, 0, 0.0, 41.0, 41, 41, 41.0, 41.0, 41.0, 41.0, 24.390243902439025, 609.0891768292682, 14.434070121951219], "isController": false}]}, function(index, item){
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
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": []}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 3503, 0, "", "", "", "", "", "", "", "", "", ""], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
