#!/bin/sh

if [ $1 == 'true' ]; then
	search='^import org\.jtb\.alogrec\.R;$'
	replace='import org.jtb.alogrec.donate.R;'
else 
        search='^import org\.jtb\.alogrec\.donate\.R;$'
        replace='import org.jtb.alogrec.R;'
fi


find src -type f -name \*.java -print0 | xargs -0 sed -i ".tmp" "s!$search!$replace!g"
