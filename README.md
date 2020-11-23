## IOt家電の独自APIです。

### swaggerについて
* ローカルでのswagger UI確認はbootRunで起動後、以下のURLを表示して下さい。
* http://localhost:8080/swagger-ui.html

// bootRunでローカル起動後、以下のコマンド実施でjsonを整形した状態でコピーできます。（※jq、pbcopyコマンドがある場合）
curl http://localhost:8080/v2/api-docs | jq . | pbcopy
