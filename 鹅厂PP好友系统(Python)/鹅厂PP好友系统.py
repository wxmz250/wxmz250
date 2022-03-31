import sys
import os
import pymysql
import tkinter.messagebox  # 弹窗库
import tkinter as tk  # GUI库
from typing import DefaultDict
from tkinter import *


def login():
    global people
    try:
        conn = pymysql.connect(host='127.0.0.1'  # 连接名称，默认127.0.0.1
                            , user='root'  # 用户名
                            , passwd='123456'  # 密码
                            , port=3306  # 端口，默认为3306
                            , db='jd'  # 数据库名称
                            , charset='utf8'  # 字符编码
                            )
        cur = conn.cursor()  # 生成游标对象
        cur.execute("SELECT * FROM `qq_user` WHERE id =%s",
                    self_userName.get())  # 执行SQL语句
        data = cur.fetchone()  # 通过fetchall方法获得数据
    except:
        tkinter.messagebox.showerror(title='登陆失败', message="请确保数据库与帮助文档一致!!!")
    try:
        if data[0] == self_userName.get() and data[1] == self_userPassword.get():
            tkinter.messagebox.showinfo(title='登录成功', message='欢迎使用鹅厂PP！')
            cur.close()  # 关闭游标
            conn.close()  # 关闭连接
            root.destroy()
            p = QQ()
            print("\n-----------------")
            print("欢迎使用鹅厂PP!!!")
            print("-----------------")
            while True:
                print("当前工作路径为:"+os.getcwd())
                n = input("是否修改工作路径?(输入1:修改;输入0:不修改;输入其他:退出程序;)\n")
                if n == "1":
                    address = input("请输入您的工作路径:")
                    try:
                        os.chdir(address)  # 运行别的代码
                    except FileNotFoundError:
                        n = input("该路径不存在!(输入-1:重新输入;输入其他:使用默认路径)\n")
                        if n != "-1":
                            print("默认分组已生成!!!")
                            print("\n您的分组情况如下:")
                            for i in people:
                                print("▶ " + i + "("+str(len(people[i]))+"人)")
                            p = QQ()
                            p.menu()
                    else:
                        print("路径修改成功!!!")
                        print("当前工作路径为:"+os.getcwd())
                        n = input("是否需要导入联系人文件:(输入1:是;输入其他:使用默认文件;)\n")
                        if n == "1":
                            p.read(n)
                        else:
                            print("默认分组已生成!!!")
                            print("\n您的分组情况如下:")
                            for i in people:
                                print("▶ " + i + "("+str(len(people[i]))+"人)")
                            p = QQ()
                            p.menu()

                elif n == "0":
                    n = input("是否需要导入联系人文件:(输入1:是;输入其他:使用默认文件;)\n")
                    if n == "1":
                        p.read(n)
                    else:
                        print("默认分组已生成!!!")
                        print("\n您的分组情况如下:")
                        for i in people:
                            print("▶ " + i + "("+str(len(people[i]))+"人)")
                        p = QQ()
                        p.menu()

                else:
                    n = input("是否确认退出本程序?(输入1:是;输入其他:使用默认分组,返回菜单)\n")
                    if n == "1":
                        n = -99
                    else:
                        print("默认分组已生成!!!")
                        print("\n您的分组情况如下:")
                        for i in people:
                            print("▶ " + i + "("+str(len(people[i]))+"人)")
                        p = QQ()
                        p.menu()
                    while n == -99:
                        print("\n-----------------\n")
                        print("感谢使用本程序!!!\n")
                        print("-----------------\n")
##                        sys.exit(0)
                        return 0

        else:
            tkinter.messagebox.showerror(title='登录失败', message='用户名或密码输入错误！')
            self_userName.delete(0, 'end')
            self_userPassword.delete(0, 'end')
            cur.close()  # 关闭游标
            conn.close()  # 关闭连接
    except:
        tkinter.messagebox.showerror(title='登录失败', message='用户名或密码输入错误！')
        self_userName.delete(0, 'end')
        self_userPassword.delete(0, 'end')
        try:
            cur.close()  # 关闭游标
            conn.close()  # 关闭连接
        except:
            pass


def register():
    global root
    global register
    def register_mysql():
        try:
            conn = pymysql.connect(host='127.0.0.1'  # 连接名称，默认127.0.0.1
                                , user='root'  # 用户名
                                , passwd='123456'  # 密码
                                , port=3306  # 端口，默认为3306
                                , db='jd'  # 数据库名称
                                , charset='utf8'  # 字符编码
                                )
            cur = conn.cursor()  # 生成游标对象
            cur.execute("SELECT * FROM `qq_user` WHERE id =%s",
                        self_user_register_Name.get())  # 执行SQL语句
            data = cur.fetchone()  # 通过fetchall方法获得数据
        except:
            tkinter.messagebox.showerror(title='注册失败', message="请确保数据库与帮助文档一致!!!")
        try:
            if data == None:
                if self_user_register_Password.get() != self_user_register_Password_1.get():
                    tkinter.messagebox.showerror(
                        title='注册失败', message="输入的两次密码不一致!!!")
                    register.deiconify()
                    self_user_register_Name.delete(0, 'end')
                    self_user_register_Password.delete(0, 'end')
                    self_user_register_Password_1.delete(0, 'end')
                elif self_user_register_Password.get()=="":
                    tkinter.messagebox.showerror(title='注册失败', message="请输入密码!!!")
                    register.deiconify()
                    self_user_register_Name.delete(0, 'end')
                    self_user_register_Password.delete(0, 'end')
                    self_user_register_Password_1.delete(0, 'end')
                else:
                    cur = conn.cursor()  # 生成游标对象
                    cur.execute("INSERT INTO qq_user  VALUES ( %s, %s)", (self_user_register_Name.get(
                    ), self_user_register_Password.get()))  # 执行SQL语句
                    tkinter.messagebox.showinfo(title='注册成功', message="注册成功!!!")
                    conn.commit()
                    register.destroy()
            else:
                tkinter.messagebox.showerror(title='注册失败', message="该用户名已存在!!!")
                register.deiconify()
                self_user_register_Name.delete(0, 'end')
                self_user_register_Password.delete(0, 'end')
                self_user_register_Password_1.delete(0, 'end')
        except:
            pass
    def unregister():
        register.destroy()
    register = Tk()
    register.title("注册")
    register.resizable(0, 0)
    # 获取屏幕宽度和高度
    scn_w, scn_h = root.maxsize()
    cen_x = (scn_w - 200) / 2
    cen_y = (scn_h - 120) / 2
    # 设置窗口初始大小和位置
    size_xy = '%dx%d+%d+%d' % (200, 130, cen_x, cen_y)
    register.geometry(size_xy)
    Label(register, text="用户名", width=6).place(x=1, y=10)
    self_user_register_Name = Entry(register, width=20)
    self_user_register_Name.place(x=45, y=10)
    Label(register, text="密码", width=6).place(x=1, y=40)
    self_user_register_Password = Entry(register, width=20, show="*")
    self_user_register_Password.place(x=45, y=40)
    Label(register, text="确认密码", width=6).place(x=1, y=70)
    self_user_register_Password_1 = Entry(register, width=19, show="*")
    self_user_register_Password_1.place(x=50, y=70)
    Button(register, text="注册", width=8,
           command=register_mysql).place(x=40, y=95)
    Button(register, text="取消", width=8, command=unregister).place(x=110, y=95)


def splitlist(list):  # 将字典转换为列表
    alist = []
    a = 0
    for sublist in list:
        try:  # 用try来判断是列表中的元素是不是可迭代的，可以迭代的继续迭代
            for i in sublist:
                alist.append(i)
        except TypeError:  # 不能迭代的就是直接取出放入alist
            alist.append(sublist)
    for i in alist:
        if type(i) == type([]):  # 判断是否还有列表
            a = + 1
            break
    if a == 1:
        return splitlist(alist)  # 还有列表，进行递归
    if a == 0:
        return alist


def Quit_QQ(n):
    n = input("是否确认退出本程序?(输入1:是;输入其他:返回主菜单)\n")
    if n == "1":
        n = -99
    else:
        p = QQ()
        p.menu()
    while n == -99:
        print("\n-----------------\n")
        print("感谢使用本程序!!!\n")
        print("-----------------\n")
##        sys.exit(0)
        return 0

class QQ:
    # 定义基本属性
    # key = ""          #组名
    # n = 0              #控制程序
    # set_value = []    #存放组员
    # people = {}       #存放组名及对应组员
    def con_mysql(self):
        # 连接数据库
        conn = pymysql.connect(host='127.0.0.1'  # 连接名称，默认127.0.0.1
                               , user='root'  # 用户名
                               , passwd='123456'  # 密码
                               , port=3306  # 端口，默认为3306
                               , db='jd'  # 数据库名称
                               , charset='utf8'  # 字符编码
                               )
        cur = conn.cursor()  # 生成游标对象
        sql = "SELECT * FROM `qq_user` WHERE id = 11"  # SQL语句
        cur.execute(sql)  # 执行SQL语句
        data = cur.fetchall()  # 通过fetchall方法获得数据
        for i in data:  # 打印输出前2条数据
            print(i)
        cur.close()  # 关闭游标
        conn.close()  # 关闭连接

    def read(self, n):
        global people
        p = QQ()
        if n == "1":
            n = input("请输入需要导入的联系人文件名:")
            try:
                global people
                people = {}
                with open(n+".txt", 'r') as f:
                    lines = f.readlines()  # 读取所有行
                    get_value = []
                    for i in range(1, len(lines)):  # 去除第一行
                        if "▼ " in lines[i]:  # 说明是组名
                            get_key = lines[i]
                            get_key = get_key.replace("▼ ", '')
                            get_key = get_key.replace("\n", '')
                            n = get_key.index("(")
                            get_key = get_key[:n]
                            get_value = []
                            people[get_key] = ""
                        else:
                            lines[i] = lines[i].replace("\n", '')
                            get_value.append(lines[i])
                            people[get_key] = get_value

                print("文件读取成功!!!")
                p.menu()
                sys.exit(0)
            except FileNotFoundError:
                n = input("该文件不存在!(输入-1:退出程序;输入其他:使用默认文件;)\n")
                if n == "-1":
                    n = input("是否确认退出本程序?(输入1:是;输入其他:使用默认分组,返回菜单)\n")
                    if n == "1":
                        n = -99
                    else:
                        people = {"特别关心": "", "我的好友": "",
                                  "朋友": "", "家人": "", "同学": ""}
                        print("默认分组已生成!!!")
                        print("\n您的分组情况如下:")
                        for i in people:
                            print("▶ " + i + "("+str(len(people[i]))+"人)")
                        p = QQ()
                        p.menu()
                    while n == -99:
                        print("\n-----------------\n")
                        print("感谢使用本程序!!!\n")
                        print("-----------------\n")
                        sys.exit(0)
                else:
                    people = {"特别关心": "", "我的好友": "",
                              "朋友": "", "家人": "", "同学": ""}
                    print("默认分组已生成!!!")
                    print("\n您的分组情况如下:")
                    for i in people:
                        print("▶ " + i + "("+str(len(people[i]))+"人)")
                    p = QQ()
                    p.menu()
        else:
            print("默认分组已生成!!!")
            print("\n您的分组情况如下:")
            for i in people:
                print("▶ " + i + "("+str(len(people[i]))+"人)")
            p = QQ()
            p.menu()

    def menu(self):
        print("\n---------QQ系统功能菜单----------")
        print("输入1:增加操作(新建分组和联系人)")
        print("输入2:删除操作(删除分组和联系人)")
        print("输入3:修改操作(修改分组和联系人)")
        print("输入4:查询操作(查询分组和联系人)")
        print("输入5:导出操作(导出分组和联系人)")
        print("输入0:清屏")
        print("输入-1:退出本程序!!!")
        n = input("请选择:")
        if n == "1":
            p = QQ()
            p.add()
        elif n == "2":
            p = QQ()
            p.dele()
        elif n == "3":
            p = QQ()
            p.update()
        elif n == "4":
            p = QQ()
            p.select()
        elif n == "5":
            print("\n当前工作路径为:"+os.getcwd())
            n = input("是否修改工作路径?(输入1:修改;输入0:不修改;输入-1:退出程序;输入其他:返回主菜单)\n")
            if n == "1":
                address = input("请输入您的工作路径:")
                try:
                    os.chdir(address)  # 运行别的代码
                except FileNotFoundError:
                    n = input("该路径不存在!(输入-1:退出程序;输入其他:返回主菜单)\n")
                    if n == "-1":
                        Quit_QQ(-99)
                    else:
                        p = QQ()
                        p.menu()
                else:
                    print("\n路径修改成功!!!")
                    print("当前工作路径为:"+os.getcwd())
                    txtname = input("请输入导出的文件名:")
                    with open(txtname+".txt", 'w') as f:
                        f.write("您的分组及其组员情况如下:\n")
                        for i in people:
                            f.write("▼ " + i+"("+str(len(people[i]))+"人)\n")
                            for j in people[i]:
                                f.write(j+"\n")
                    print(txtname+".txt文件已生成!!!")
                    print("文件路径为:"+os.getcwd()+"\\"+txtname+".txt")
            elif n == "0":
                txtname = input("请输入导出的文件名:")
                with open(txtname+".txt", 'w') as f:
                    f.write("您的分组及其组员情况如下:\n")
                    for i in people:
                        f.write("▼ " + i+"("+str(len(people[i]))+"人)\n")
                        for j in people[i]:
                            f.write(j+"\n")
                print(txtname+".txt文件已生成!!!")
                print("文件路径为:"+os.getcwd()+"\\"+txtname+".txt")
                p = QQ()
                p.menu()
            elif n == "-1":
                Quit_QQ(-99)
            else:
                p = QQ()
                p.menu()
        elif n == "0":
            print("\n"*40)
            p = QQ()
            p.menu()
        elif n == "-1":
            Quit_QQ(-99)
        else:
            global x
            x = x-1
            if x > 0:
                print("输入错误!!!")
                print("\n输入错误,您还剩%d次输入机会,请重新输入!!!" % x)
                p = QQ()
                p.menu()
            print("\n-----------------\n")
            print("感谢使用本程序!!!\n")
            print("-----------------\n")
            os._exit(0) #终止shell

    def reserve(self, key, value):  # 组员备注
        global people
        n = 0  # 控制程序
        set_value = []  # 存放组员
        for i in people:
            if key == i:
                for j in people[i]:
                    set_value.append(j)
                set_value = splitlist(set_value)  # 将字典转换为列表
                if value in set_value:
                    n = input("该分组已存在该联系人,是否备注?"
                              "(输入1:备注；输入0:不备注；输入-1：退出程序；输入其他:返回菜单!)\n")
                    if n == "1":
                        bz = input("请输入备注名:\n")
                        value = value+"("+bz+")"
                        return value
                    elif n == "0":
                        return value
                    elif n == "-1":
                        n = -99
                    else:
                        p = QQ()
                        p.menu()
                    Quit_QQ(n)
                return value

    def add(self):  # 增加联系人及组名
        global people
        n = 0  # 控制程序
        print("\n您的分组情况如下:")
        for i in people:
            print("▶ " + i + "("+str(len(people[i]))+"人)")
        n = input("输入1:新建分组；输入0:新建联系人；输入-1:退出程序；输入其他:返回菜单!\n")
        if n == "1":
            key = input("请输入您想新建的分组名:\n")
            if key not in people:
                people[key] = ""
                print("该分组创建成功!")
            else:
                n = input("该分组已存在!!!(输入-1:退出程序；输入其他:返回主菜单!)\n")
                if n == "-1":
                    n = -99
                else:
                    p = QQ()
                    p.menu()
        elif n == "0":
            key = input("请输入您想增加的联系人所在分组:\n")
            value = input("请输入您想增加的联系人:\n")
            if key not in people:
                set_value = []  # 存放组员
                n = input("该分组不存在,是否创建?"
                          "(输入1:创建分组；输入0:划分到默认分组；输入-1:退出程序；输入其他:返回菜单!)\n")
                if n == "1":
                    set_value.append(value)
                    print("该分组创建成功!")
                    people[key] = set_value
                    print("联系人添加成功!")
                elif n == "0":
                    set_value = []  # 存放组员
                    for i in people:
                        if i == "我的好友":
                            p = QQ()
                            a = p.reserve("我的好友", value)
                            if a == "-1":
                                print("联系人添加失败!")
                            else:
                                for j in people["我的好友"]:
                                    if j != "":
                                        set_value.append(j)
                                set_value.append(a)
                                people["我的好友"] = set_value
                                print("联系人添加成功!")
                elif n == "-1":
                    n = -99
                else:
                    p = QQ()
                    p.menu()
            else:
                set_value = []  # 存放组员
                for i in people:
                    if i == key:
                        p = QQ()
                        a = p.reserve(key, value)
                        if a == "-1":
                            print("联系人添加失败!")
                        else:
                            for j in people[i]:
                                if j != "":
                                    set_value.append(j)
                            set_value.append(a)
                            people[key] = set_value
                            print("联系人添加成功!")
        elif n == "-1":
            n = -99
        else:
            p = QQ()
            p.menu()

        Quit_QQ(n)

    def dele(self):  # 删除联系人,同名联系人及空组
        global people
        n = 0  # 控制程序
        set_value = []  # 存放组员
        print("\n您的分组情况如下:")
        for i in people:
            print("▶ " + i + "("+str(len(people[i]))+"人)")
        n = input("输入1:删除分组；输入0:删除联系人；输入-1:退出程序；输入其他:返回菜单!\n")
        if n == "1":
            while True:
                key = input("请输入您想删除的分组名:")
                if key in people:
                    if len(people[key]) > 0:
                        n = input(
                            "该分组下有%s人,是否确认删除?(输入1:是;输入0:重新输入;输入-1:退出程序;输入其他:返回菜单！)\n" % (len(people[key])))
                        if n == "1":
                            del people[key]
                            print("分组删除成功!")
                            n = 0
                        elif n == "0":
                            n = "1"
                        elif n == "-1":
                            n = -99
                        else:
                            p = QQ()
                            p.menu()
                    else:
                        del people[key]
                        print("分组删除成功!")
                        n = 0
                else:
                    n = input("该分组不存在,是否重新输入?"
                              "(输入1:重新输入；输入-1:返回主菜单；输入其他:退出程序!)\n")
                    if n == "1":
                        n = "1"
                    elif n == "-1":
                        n = -99
                    else:
                        p = QQ()
                        p.menu()
                if n != "1":
                    break

        elif n == "0":
            key = input("请输入您想删除的联系人所在分组:\n")
            if key not in people:  # 不存在该组
                n = input("该分组不存在,是否重新输入?"
                          "(输入1:重新输入；输入-1:退出程序；输入其他:返回主菜单!)\n")
                if n == "1":
                    p = QQ()
                    p.dele()
                elif n == "-1":
                    n = -99
                else:
                    p = QQ()
                    p.menu()
            else:  # 存在该组
                print("\n当前分组及其组员情况如下:")
                for i in people:
                    if i == key:
                        print("▼ " + key+"("+str(len(people[key]))+"人)")
                        for j in people[key]:
                            print(j)
                value = input("请输入您想删除的联系人:\n")
                if people[key] == "":
                    n = input("该分组为空,是否删除该分组?"
                              "(输入1:是；输入-1:否；输入其他:退出程序!)\n")
                    if n == "1":
                        people.pop(key)
                        print("分组删除成功!")
                    elif n == "-1":
                        print("该分组不存在该联系人")
                        print("联系人删除失败!")
                    else:
                        n = -99
                elif value in people[key]:
                    for j in people[key]:
                        set_value.append(j)
                    set_value.remove(value)
                    people[key] = set_value
                    print("联系人删除成功!")
                else:
                    n = input("该分组并无该联系人!是否重新输入?"
                              "(输入1:是；输入-1:返回菜单；输入其他:退出程序!)\n")
                    if n == "1":
                        p = QQ()
                        p.dele()
                    elif n == "-1":
                        p = QQ()
                        p.menu()
                    else:
                        n = -99
        elif n == "-1":
            n = -99
        else:
            p = QQ()
            p.menu()
        Quit_QQ(n)

    def update(self):  # 修改联系人及组名
        global people
        n = 0  # 控制程序
        set_value = []  # 存放组员
        n = input("请输入您想修改的类型:"
                  "(输入1:修改分组名；输入0:修改联系人；输入-1:退出程序;输入其他:返回菜单!)\n")
        if n == "1":
            print("您的分组情况如下:")
            for i in people:
                print(i)
            set_key = input("请输入您想修改的分组名:")
            if set_key in people:
                new_key = input("请输入新分组名:")
                if new_key in people:
                    n = input("该组名已存在!!!(输入-1:退出程序;输入其他:返回菜单!)\n")
                    if n == "-1":
                        n = -99
                    else:
                        p = QQ()
                        p.menu()
                else:
                    people[new_key] = people.pop(set_key)
                    print("组名修改成功")
            else:
                n = input("该分组并无该组名!是否重新输入?"
                          "(输入1:是；输入-1:退出程序；输入其他:返回菜单!)\n")
                if n == "1":
                    p = QQ()
                    p.update()
                elif n == "-1":
                    n = -99
                else:
                    p = QQ()
                    p.menu()
        elif n == "0":
            set_key = input("请输入您联系人所在分组名:")
            set_value = []  # 存放组员
            if set_key in people:
                print("该分组的联系人如下:")
                for i in people[set_key]:
                    print(i)
                name = input("请输入您想修改的联系人:")
                new_name = input("请输入一个新联系人:")
                if name in people[set_key]:
                    for i in people[set_key]:
                        if i == name:
                            set_value.append(new_name)
                        else:
                            set_value.append(i)
                    people[set_key] = set_value
                    print("联系人修改成功!")
                else:
                    n = input("该分组并无该联系人!是否重新输入?"
                              "(输入1:是；输入-1:退出程序；输入其他:返回菜单!)\n")
                    if n == "1":
                        p = QQ()
                        p.update()
                    elif n == "-1":
                        n = -99
                    else:
                        p = QQ()
                        p.menu()
            else:
                print("该分组名不存在!")
                print("您的分组情况如下:")
                for i in people:
                    print(i)
                n = input("是否重新输入?(输入1:是；输入-1:退出程序；输入其他:返回菜单!)\n")
                if n == "1":
                    p = QQ()
                    p.update()
                elif n == "-1":
                    n = -99
                else:
                    p = QQ()
                    p.menu()
        elif n == "-1":
            n = -99
        else:
            p = QQ()
            p.menu()
        Quit_QQ(n)

    def select(self):  # 输出联系人及组名
        global people
        n = input(
            "输入1:列出所有分组；输入2:列出各分组名及其组员；输入3:列出指定分组名及其组员；输入-1:退出程序；输入其他:返回菜单!\n")
        if n == "1":
            print("您的分组情况如下:")
            for i in people:
                print("▶ " + i + "("+str(len(people[i]))+"人)")
        elif n == "2":
            print("您的分组及其组员情况如下:")
            for i in people:
                print("▼ " + i+"("+str(len(people[i]))+"人)")
                for j in people[i]:
                    print(j)
        elif n == "3":
            set_key = input("请输入您想查看的分组:")
            if set_key not in people:
                n = input("该组名不存在！(输入-1:退出程序；输入其他:返回菜单!)\n")
                if n == "1":
                    p = QQ()
                    p.update()
                elif n == "-1":
                    n = -99
                else:
                    p = QQ()
                    p.menu()
            else:
                print("该分组及其组员情况如下:")
                for i in people:
                    if i == set_key:
                        print("▼ " + i+"("+str(len(people[i]))+"人)")
                        for j in people[i]:
                            print(j)
        elif n == "-1":
            n = -99
        else:
            p = QQ()
            p.menu()
        Quit_QQ(n)


x = 3   #菜单输入错误的次数为3
root = Tk()
people = {"特别关心": "", "我的好友": "", "朋友": "", "家人": "", "同学": ""}
root.title("登录")
root['width'] = 200
root['height'] = 120
root.resizable(0, 0)
# 获取屏幕宽度和高度
scn_w, scn_h = root.maxsize()
cen_x = (scn_w - 200) / 2
cen_y = (scn_h - 120) / 2
# 设置窗口初始大小和位置
size_xy = '%dx%d+%d+%d' % (200, 120, cen_x, cen_y)
root.geometry(size_xy)
Label(root, text="用户名", width=6).place(x=1, y=10)
self_userName = Entry(root, width=20)
self_userName.place(x=45, y=10)
Label(root, text="密码", width=6).place(x=1, y=40)
self_userPassword = Entry(root, width=20, show="*")
self_userPassword.place(x=45, y=40)
Button(root, text="登录", width=8, command=login).place(x=40, y=75)
Button(root, text="注册", width=8, command=register).place(x=110, y=75)
# root.deiconify()
