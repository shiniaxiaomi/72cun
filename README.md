# 72cun-springboot

发布流程：

前端
1.运行 npm run build,进行本地打包
2.xshell运行 pm2 delete 0,关闭原有的前端进程
3.将本地打包好的.nuxt文件上传至服务器中
4.xshell运行命令
    1). cd /nuxt
    2). pm2 start npm --name "72cun-nuxt" -- run start

后端
1.maven打包上传
2.运行命令 /bin/bash /72cun.sh