# BroadCastDemo
广播使用阶段性复习
1. 发现:创建一个标准广播发送接收的demo时，如果定义的是外部类可以正常执行，如果定义的是内部类，则这个类要定义为静态的。
2. 原因分析：Androidmanifest文件实在程序加载开始就执行的，如果不是静态的内部的话，该类不在程序加载开始就载入，会产生错误。
