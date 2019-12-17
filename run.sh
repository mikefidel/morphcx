mvn clean package

#java -p mods -m morphcx.launcher.poi/ndextools.morphcx.launcher.poi.ExportPOI -h -i inp.txt
java -p mods -m morphcx.launcher.csv/ndextools.morphcx.launcher.csv.ExportCSV -X -i inp.txt -o out.txt -n windows -t csv
