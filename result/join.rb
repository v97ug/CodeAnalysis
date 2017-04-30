puts File.read("method-parsed.txt")
    .gsub("\n"," ")
    .gsub(/\t+/," ")
    .gsub(/ +/," ")
