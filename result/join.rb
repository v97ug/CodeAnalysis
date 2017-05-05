join_str = File.read("java-parsed.txt")
    .gsub("\n"," ")
    .gsub(/\t+/," ")
    .gsub(/ +/," ")

File.write("join-result/parsed-joined.txt",join_str)