require 'open-uri'
require 'nokogiri'

def get_content(uri)
  charset, html = open(uri) { |f| [f.charset, f.read] }
  Nokogiri::HTML.parse(html, nil, charset)
end

for i in 1..10 do
  uri = "https://github.com/search?l=Java&o=desc&p=#{i}&q=API&s=stars&type=Repositories&utf8=%E2%9C%93"

  repository_names = get_content(uri).xpath('//*[@class="repo-list-item d-flex flex-justify-start py-4 public source"]/div/h3/a').map{|a| a.text}
  repository_names.map do |repo_name|
    system("git clone https://github.com/#{repo_name}")
  end
end