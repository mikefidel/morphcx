mvn clean package

#java -p mods -m morphcx.launcher.poi/ndextools.morphcx.launcher.poi.ExportPOI -h
#java -p mods -m morphcx.launcher.csv/ndextools.morphcx.launcher.csv.ExportCSV -h
java -p mods -m morphcx.launcher.poi/ndextools.morphcx.launcher.poi.ExportPOI -X -i inp.txt -o out.txt
java -p mods -m morphcx.launcher.csv/ndextools.morphcx.launcher.csv.ExportCSV -X -i inp.txt -o out.txt -t comma -n linux
