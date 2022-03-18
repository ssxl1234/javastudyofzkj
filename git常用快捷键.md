# GIT快速入门

## 常用快捷键



## 远程仓库
> 1、创建sshkey。并配置远程仓库的pubkey。远程仓库将使用公钥来验证是否是当事人提交的更新。
ssh-keygen -t rsa -C "emailadress"
 默认会在用户目录创建.ssh/id_rsa,id_rsa.pub 私钥和公钥文件。
 2、登录github并配置账户设置sshkey公钥内容。并创建一个远程git仓库:name.git。
 3、将本地仓库与远程仓库fetch起来。
git remote add origin git@github.com:loginname/name.git
orgin本地显示的远程仓库名。
4、推送本地仓库版本到远程仓库
第一次：
git push -u orgin master         #推送最新master分支并关联
后续：
git push orgin master
5、删除更改远程仓库（只是解除绑定，并非删除远程仓库内容）
git remote -v       #查看远程仓库版本
git remote rm name          #解除与指定远程仓库的绑定