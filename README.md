# Code Analysis

javaでかかれたAPIをGithubから複数取得し、そのコードを解析する。
具体的には、APIのメソッド間の関連度を、word embeddingを使って調べる。

# 手順
1. 
```bash
$ ruby get_API_code.rb
```

2. Main.java を実行する
3. 
```bash
$ ruby join.rb
```
を実行して、`join-result/parsed-joined.txt`がword embeddingの入力ファイルとなる。

# おまけ
`javadoc/index.html` を開くと、javadocが見られる。