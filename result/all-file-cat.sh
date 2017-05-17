#!/usr/bin/env bash

find all-files/ -name java-parsed.txt | xargs cat > all-java-parsed.txt
find all-files/ -name method-names.txt | xargs cat > all-method-names.txt