join_str = File.read("method-parsed.txt")
    .gsub("\n"," ")
    .gsub(/\t+/," ")
    .gsub(/ +/," ")

File.write("join-result/method-joined.txt",join_str)