/Users/dima/Downloads/apache-jmeter-5.5/bin/jmeter -n -t Test_Plan_task_20_Oleksyuk_Dmytro.jmx

/Users/dima/Downloads/apache-jmeter-5.5/bin/jmeter -n -t User_Defined_Variables.jmx -Jduration=10

/Users/dima/Downloads/apache-jmeter-5.5/bin/jmeter -g summary -o report

-n –t home_work.jmx -Jload_profile="const(10,10s) line(10,100,1m) step(5,25,5,1h)" -Jduration=100

-n -t load.jmx -l summary.csv -e -o path_to_web_report_folder