#include <stdio.h>
#include <stdlib.h>
#include<string.h>
#include <windows.h>
#define maxsize 1000 //最大串长
typedef char SString[maxsize]; //串的定长顺序存储表示,0 号单元存放串的长度
int next[maxsize];
//KMP 算法中用到的 next 输入要输入单词的个数 n依 次 输 入 单 词m1,m2,..
//mn将 mi 与文本中的单词进行 KMP 匹配输出匹配结果（行号和列号)文本是否读完？
void color(short x)//局部字体改变颜色
{
    if (x==12)
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 12);//改变字体颜色为红色
    else//默认的颜色白色
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
}
void menu()
{
    char name[100]; //存储输入的小说路径字符串
    SString words[100]; //定义字符串数组， 用于存储输入的关键字
    int n,i,m;
    FILE *fp;
    char ch;
    printf("\t\t\t *********** 欢迎使用文学研究系统 ***********\n");
    printf("\t\t\t┌────────┌───┐────────┐\n");
    printf("\t\t\t│                │ 菜单 │                │\n");
    printf("\t\t\t│                └───┘                │\n");
    printf("\t\t\t│*******        0.退出系统          *******│\n");
    printf("\t\t\t│*******        1.查询文本关键字    *******│\n");
    printf("\t\t\t│*******        2.输出文本内容      *******│\n");
    printf("\t\t\t└─────────────────────┘\n");
    printf("\t\t\t******您的选择是？******\n");
    printf("\t\t\t");
    scanf("%d",&m);
    if(m<0||m>2)
    {
        color(12);
        printf("\t\t\t输入错误!请重新输入！！！\n");
        color(0);
        Sleep(2000);//暂停3秒
        system("cls");
        menu();
    }
    else
    {
            if(m==0)
            {
            printf("\t\t\t********感谢您的使用，再见！！！************");
            exit(0);
            }
            else
                switch(m)
                    {
                        case(1):
                                printf("\t\t\t请输入已创建的文本文件的路径 (如 D:\\novel.txt):\n");
                                printf("\t\t\t");
                                scanf("%s",name); //用户输入包含路径的文本文件名
                                printf("\t\t\t请输入要查找的单词数： \n");
                                printf("\t\t\t");
                                scanf("%d",&n); //用户输入要查找的关键字个数
                                printf("\t\t\t");
                                printf("请输入要查找的单词， 词与词之间用空格隔开 (区分大小写):\n");
                                for(i=0;i<n;i++)
                                {
                                    printf("\t\t\t");
                                    scanf("%s",&words[i][1]); //用 户 一 次 性 输 入 要 查 找 的 关 键 字 ，words[i][0]用于存放字符串的长度
                                }
                                for(i=0;i<n;i++)
                                find(name,words[i]);
                                printf("\n\t\t\t*****输入1返回菜单,或按下其他键退出系统*****\n");
                                printf("\t\t\t");
                                scanf("%d",&m);
                            if(m==1)
                                {
                                    system("cls");
                                    menu();
                                }
                            else
                            {
                                 printf("\t\t\t******感谢您的使用，再见！！！******");
                                 exit(0);
                            }
                        break;
                        case(2):
                                printf("\t\t\t请输入已创建的文本文件的路径 (如 D:\\novel.txt):\n");
                                printf("\t\t\t");
                                scanf("%s",name); //用户输入包含路径的文本文件名
                                fp=fopen(name,"r");
                                if (!(fp=(fopen(name,"r")))) //打开小说文件
                                    {
                                        printf("\t\t\t");
                                        color(12);
                                        printf("\n\t\t\t未找到该文件！！！\n");
                                        color(0);
                                        printf("\t\t\t");
                                        printf("\n\t\t\t*****输入1返回菜单,或按下其他键退出系统*****\n");
                                        printf("\t\t\t");
                                        scanf("%d",&m);
                                        if(m==1)
                                            {
                                                system("cls");
                                                menu();
                                            }
                                        else
                                            {
                                                printf("\t\t\t******感谢您的使用，再见！！！******");
                                                exit(0);
                                            }
                                    }
                                else
                                {
                                    system("cls");
                                    printf("**********该文本内容如下：************\n");
                                    while(!feof(fp))
                                        {

                                            putchar(ch);
                                            fscanf(fp,"%c",&ch);
                                        }
                                    fclose(fp);
                                    printf("\n*****输入1返回菜单,或按下其他键退出系统*****\n");
                                    scanf("%d",&m);
                                    if(m==1)
                                        {
                                            system("cls");
                                            menu();
                                        }
                                    else
                                        {
                                            printf("\t\t\t******感谢您的使用，再见！！！******");
                                            exit(0);
                                        }
                                }
                        break;
                    }
    }
}
void get_next(SString T,int next[ ])//求模式串 T的 next函数值并存入数组 next
{
    int j=1,k=0;
    next[1]=0;
    while (j<T[0])
    {
        if (k==0 || T[k]==T[j])
            {
                ++j; ++k;
                if (T[j]!=T[k]) next[j]=k;
                else next[j]=next[k];
            }
        else k=next[k];
    }
}
int Index(SString S,SString T,int pos)// 利用模式串 T 的 next 函数求 T 在主串 S 中第 pos 个字符之后的位置的 KMP 算法
{
    int i=pos,j=1;
    while (i<=S[0]&&j<=T[0])
    {
        if (j==0||S[i]==T[j]) {++i;++j;} // 继续比较后继字符
        else j=next[j]; // 模式串向右移动
    }
        if (j>T[0]) return (i-T[0]); //匹配成功
        else return 0; // 匹配失败
}
int length(SString str) //求串长
{
    int i=1;
    while(str[i]) i++;
    return(i-1);
}
void find(char name[],SString keys) //对于输入的每一个要查找的关键字， 从小说文件中逐行读取字符串查找
{
    int m;
    char ch;
    SString text; //存放从小说文件读取的一行字符串
    int i=1,j=0,k; //i 用于存放行号， j 用于存放列号,k 用于输出格式的控制
    int n=0; //n 记录出现次数
    FILE *fp;
    if (!(fp=(fopen(name,"r+")))) //打开小说文件
    {
        printf("\t\t\t");
        color(12);
        printf("\n\t\t\t未找到该文件！！！\n");
        color(0);
        printf("\t\t\t");
        printf("\n\t\t\t*****输入1返回菜单,或按下其他键退出系统*****\n");
        printf("\t\t\t");
        scanf("%d",&m);
        if(m==1)
        {
             system("cls");
             menu();
        }
        else
        {
            printf("\t\t\t");
            printf("******感谢您的使用，再见！！！******");
            exit(0);
        }
    }
        keys[0]=length(keys); //调用 length 函数求关键字的长度
        get_next(keys,next); //调用 get_next 函数求模式串（关键字）每一个字符对应的 next 值
        printf("\t\t\t");
        printf("\n\t\t\t%s出现于:\n",&keys[1]); //打印关键字
        while(!feof(fp)) //如果还没到小说文件末尾
            {
                k=0;
                fgets(&text[1],maxsize,fp);//从小说文件中读取一行字符串， 存入 text 串中
                text[0]=length(text); //求读入的串的长度
                j=Index(text,keys,j+1); //调用 KMP 算法， 统计关键字在该行出现的位置， 若匹配不成功则返回 0
                if (j!=0)
                { printf("\t\t\t行=%d,列=%d",i,j); k++; n++;} //若匹配成功则打印行号和列号
                while(j!=0) //若该行找到了关键字， 则继续寻找看是否还能匹配成功
                {
                    j=Index(text,keys,j+1); //调用 KMP 算法从刚找到的列号后一字符起匹配
                    if (j!=0)
                    {n++;printf(",%d",j);} //若匹配成功， 则打印列号
                }
                i++; //行号加 1， 在下一行中寻找
                if(k) printf("\n"); //输出格式控
            }
    printf("\t\t\t");
    printf("%s 共出现%d 次\n",&keys[1],n);
}
void main()
{
    int w;
    menu();
    return 0;
}
