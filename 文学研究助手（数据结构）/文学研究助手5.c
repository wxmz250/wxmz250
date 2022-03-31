#include <stdio.h>
#include <stdlib.h>
#include<string.h>
#include <windows.h>
#define maxsize 1000 //��󴮳�
typedef char SString[maxsize]; //���Ķ���˳��洢��ʾ,0 �ŵ�Ԫ��Ŵ��ĳ���
int next[maxsize];
//KMP �㷨���õ��� next ����Ҫ���뵥�ʵĸ��� n�� �� �� �� �� ��m1,m2,..
//mn�� mi ���ı��еĵ��ʽ��� KMP ƥ�����ƥ�������кź��к�)�ı��Ƿ���ꣿ
void color(short x)//�ֲ�����ı���ɫ
{
    if (x==12)
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 12);//�ı�������ɫΪ��ɫ
    else//Ĭ�ϵ���ɫ��ɫ
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
}
void menu()
{
    char name[100]; //�洢�����С˵·���ַ���
    SString words[100]; //�����ַ������飬 ���ڴ洢����Ĺؼ���
    int n,i,m;
    FILE *fp;
    char ch;
    printf("\t\t\t *********** ��ӭʹ����ѧ�о�ϵͳ ***********\n");
    printf("\t\t\t����������������������������������������������\n");
    printf("\t\t\t��                �� �˵� ��                ��\n");
    printf("\t\t\t��                ����������                ��\n");
    printf("\t\t\t��*******        0.�˳�ϵͳ          *******��\n");
    printf("\t\t\t��*******        1.��ѯ�ı��ؼ���    *******��\n");
    printf("\t\t\t��*******        2.����ı�����      *******��\n");
    printf("\t\t\t����������������������������������������������\n");
    printf("\t\t\t******����ѡ���ǣ�******\n");
    printf("\t\t\t");
    scanf("%d",&m);
    if(m<0||m>2)
    {
        color(12);
        printf("\t\t\t�������!���������룡����\n");
        color(0);
        Sleep(2000);//��ͣ3��
        system("cls");
        menu();
    }
    else
    {
            if(m==0)
            {
            printf("\t\t\t********��л����ʹ�ã��ټ�������************");
            exit(0);
            }
            else
                switch(m)
                    {
                        case(1):
                                printf("\t\t\t�������Ѵ������ı��ļ���·�� (�� D:\\novel.txt):\n");
                                printf("\t\t\t");
                                scanf("%s",name); //�û��������·�����ı��ļ���
                                printf("\t\t\t������Ҫ���ҵĵ������� \n");
                                printf("\t\t\t");
                                scanf("%d",&n); //�û�����Ҫ���ҵĹؼ��ָ���
                                printf("\t\t\t");
                                printf("������Ҫ���ҵĵ��ʣ� �����֮���ÿո���� (���ִ�Сд):\n");
                                for(i=0;i<n;i++)
                                {
                                    printf("\t\t\t");
                                    scanf("%s",&words[i][1]); //�� �� һ �� �� �� �� Ҫ �� �� �� �� �� �� ��words[i][0]���ڴ���ַ����ĳ���
                                }
                                for(i=0;i<n;i++)
                                find(name,words[i]);
                                printf("\n\t\t\t*****����1���ز˵�,�����������˳�ϵͳ*****\n");
                                printf("\t\t\t");
                                scanf("%d",&m);
                            if(m==1)
                                {
                                    system("cls");
                                    menu();
                                }
                            else
                            {
                                 printf("\t\t\t******��л����ʹ�ã��ټ�������******");
                                 exit(0);
                            }
                        break;
                        case(2):
                                printf("\t\t\t�������Ѵ������ı��ļ���·�� (�� D:\\novel.txt):\n");
                                printf("\t\t\t");
                                scanf("%s",name); //�û��������·�����ı��ļ���
                                fp=fopen(name,"r");
                                if (!(fp=(fopen(name,"r")))) //��С˵�ļ�
                                    {
                                        printf("\t\t\t");
                                        color(12);
                                        printf("\n\t\t\tδ�ҵ����ļ�������\n");
                                        color(0);
                                        printf("\t\t\t");
                                        printf("\n\t\t\t*****����1���ز˵�,�����������˳�ϵͳ*****\n");
                                        printf("\t\t\t");
                                        scanf("%d",&m);
                                        if(m==1)
                                            {
                                                system("cls");
                                                menu();
                                            }
                                        else
                                            {
                                                printf("\t\t\t******��л����ʹ�ã��ټ�������******");
                                                exit(0);
                                            }
                                    }
                                else
                                {
                                    system("cls");
                                    printf("**********���ı��������£�************\n");
                                    while(!feof(fp))
                                        {

                                            putchar(ch);
                                            fscanf(fp,"%c",&ch);
                                        }
                                    fclose(fp);
                                    printf("\n*****����1���ز˵�,�����������˳�ϵͳ*****\n");
                                    scanf("%d",&m);
                                    if(m==1)
                                        {
                                            system("cls");
                                            menu();
                                        }
                                    else
                                        {
                                            printf("\t\t\t******��л����ʹ�ã��ټ�������******");
                                            exit(0);
                                        }
                                }
                        break;
                    }
    }
}
void get_next(SString T,int next[ ])//��ģʽ�� T�� next����ֵ���������� next
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
int Index(SString S,SString T,int pos)// ����ģʽ�� T �� next ������ T ������ S �е� pos ���ַ�֮���λ�õ� KMP �㷨
{
    int i=pos,j=1;
    while (i<=S[0]&&j<=T[0])
    {
        if (j==0||S[i]==T[j]) {++i;++j;} // �����ȽϺ���ַ�
        else j=next[j]; // ģʽ�������ƶ�
    }
        if (j>T[0]) return (i-T[0]); //ƥ��ɹ�
        else return 0; // ƥ��ʧ��
}
int length(SString str) //�󴮳�
{
    int i=1;
    while(str[i]) i++;
    return(i-1);
}
void find(char name[],SString keys) //���������ÿһ��Ҫ���ҵĹؼ��֣� ��С˵�ļ������ж�ȡ�ַ�������
{
    int m;
    char ch;
    SString text; //��Ŵ�С˵�ļ���ȡ��һ���ַ���
    int i=1,j=0,k; //i ���ڴ���кţ� j ���ڴ���к�,k ���������ʽ�Ŀ���
    int n=0; //n ��¼���ִ���
    FILE *fp;
    if (!(fp=(fopen(name,"r+")))) //��С˵�ļ�
    {
        printf("\t\t\t");
        color(12);
        printf("\n\t\t\tδ�ҵ����ļ�������\n");
        color(0);
        printf("\t\t\t");
        printf("\n\t\t\t*****����1���ز˵�,�����������˳�ϵͳ*****\n");
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
            printf("******��л����ʹ�ã��ټ�������******");
            exit(0);
        }
    }
        keys[0]=length(keys); //���� length ������ؼ��ֵĳ���
        get_next(keys,next); //���� get_next ������ģʽ�����ؼ��֣�ÿһ���ַ���Ӧ�� next ֵ
        printf("\t\t\t");
        printf("\n\t\t\t%s������:\n",&keys[1]); //��ӡ�ؼ���
        while(!feof(fp)) //�����û��С˵�ļ�ĩβ
            {
                k=0;
                fgets(&text[1],maxsize,fp);//��С˵�ļ��ж�ȡһ���ַ����� ���� text ����
                text[0]=length(text); //�����Ĵ��ĳ���
                j=Index(text,keys,j+1); //���� KMP �㷨�� ͳ�ƹؼ����ڸ��г��ֵ�λ�ã� ��ƥ�䲻�ɹ��򷵻� 0
                if (j!=0)
                { printf("\t\t\t��=%d,��=%d",i,j); k++; n++;} //��ƥ��ɹ����ӡ�кź��к�
                while(j!=0) //�������ҵ��˹ؼ��֣� �����Ѱ�ҿ��Ƿ���ƥ��ɹ�
                {
                    j=Index(text,keys,j+1); //���� KMP �㷨�Ӹ��ҵ����кź�һ�ַ���ƥ��
                    if (j!=0)
                    {n++;printf(",%d",j);} //��ƥ��ɹ��� ���ӡ�к�
                }
                i++; //�кż� 1�� ����һ����Ѱ��
                if(k) printf("\n"); //�����ʽ��
            }
    printf("\t\t\t");
    printf("%s ������%d ��\n",&keys[1],n);
}
void main()
{
    int w;
    menu();
    return 0;
}
