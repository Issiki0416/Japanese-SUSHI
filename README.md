# Japanese-SUSHI
- オンライン上でSUSHIを販売するECサイト   

## 作成目的：MVCを意識したECサイト構築

## 内容：学校の課題をMVCを意識して再構築する

## ![image](https://user-images.githubusercontent.com/96870513/198242691-835fe662-2fe0-4614-b917-d271ea9c3ccf.png)

## 機能

- 商品一覧

- カート(追加/削除)

- 購入

- ログイン/ログアウト



## 整理

## トップ　index.jsp
- menu、footerをincludeして表示
- menuの箇所でサーブレットを指定している。

## 商品一覧　showItem.jsp　(ShowItemServlet)
- サーブレット側は簡単なメソッドの呼び出しだけですげてのレコードを取得できる。
- 処理はdaoに投げる。
- ItemDAO()をインスタンス化してdaoのメソッドを実行
- 取得したlistを(表示に必要なデータを)属性としてリクエストスコープにセットする
- サーブレットからshoItem.jspに画面遷移。ここでリクエストをフォワードさせる。itemsという属性名
- ![image](https://user-images.githubusercontent.com/96870513/198220825-bc7709ba-28ad-4846-bf13-9f73fa7217f2.png)
- forをぶん回す
- 商品をついかしたとき、actionにaddをつけてCartServletになげる。
  - 最初の一回目はカートを作成する（CartBean）
        - CartBeanを作って、cartにいれる。それをセッションに設定する
  - getSessionをtrueなのでセッションが開始してないなら新しく開始
  - ItemDaoから商品を探してそれをcartに追加



## 購入　purchase.jsp　(PurchaseServlet)
- actionがついてないのでpurchase.jspに遷移
- 

## カート 　cart.jsp
- 商品削除
  - CartBeanのメソッドに商品IDを渡して商品を削除


## ログイン　login.jsp
- actionがないならlogin.jspに遷移
- ログインチェックはCustomerDaoに投げて処理をさせる
- 名前とパスをCustomerBeanにいれる
- CustomerBeanが入っていたらセッションを発行(customer)


## ログアウト　logout.jsp
- actionがないならlogout.jspに遷移
- actionにlogoutあるならLogoutServletでセッションスコープを削除する


