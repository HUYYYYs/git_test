#include "Contact.h"

static int check_capacity(Contact* ps)
{
	if (ps->sz == ps->capacity)
	{
		PeoInfo* ptr = (PeoInfo*)realloc(ps->data, (ADD_SZ + ps->capacity) * sizeof(PeoInfo));
		if (ptr != NULL)
		{
			ps->data = ptr;
			ps->capacity += ADD_SZ;
			printf("增容成功\n");
			return 1;
		}
		else
		{
			perror("check_capacity:");
			return 0;
		}
	}
	else return 1;
}

void LoadContact(Contact* ps)
{
	FILE* pf = fopen("E:/Computer.txt", "rb");
	if (pf == NULL)
	{
		perror("LoadContact::fopen");
		return;
	}
	PeoInfo ptr = { 0 };
	while (fread(&ptr, sizeof(PeoInfo), 1, pf))
	{
		check_capacity(ps);
		ps->data[ps->sz] = ptr;
		ps->sz++;
	}
	fclose(pf);
	pf = NULL;
}

void InitContact(Contact* ps)
{
	assert(ps);
	ps->data = (PeoInfo*)malloc(SIZE * sizeof(PeoInfo));
	if (ps->data == NULL)
	{
		perror("InitContact()");
		return;
	}
	ps->sz = 0;
	ps->capacity = SIZE;
}

void DestroyContact(Contact* ps)
{
	free(ps->data);
	ps->data = NULL;
	ps->capacity = 0;
	ps->sz = 0;
}

void SaveContact(Contact* ps)
{
	FILE* pb = fopen("E://Address Book.txt", "w");
	FILE* pc = fopen("E://Computer.txt", "wb");
	if (pb == NULL || pc == NULL)
	{
		perror("SaveContact");
		return;
	}
	int i = 0;
	for (i = 0; i < ps->sz; i++)
	{
		fprintf(pb, "姓名：%s  性别：%s  地址：%s  工作：%s  电话：%s  年龄：%d\n", ps->data[i].name, ps->data[i].gender, ps->data[i].address, ps->data[i].work, ps->data[i].number, ps->data[i].age);
		fwrite(&(ps->data[i]), sizeof(PeoInfo), 1, pc);
	}
	fclose(pb);
	fclose(pc);
	pb = NULL;
	pc = NULL;
}

static int FindByName(const char name[], const struct Contact* pf)
{
	assert(name && pf);
	int i = 0;
	for (i = 0; i < pf->sz; i++)
	{
		if (0 == strcmp(pf->data[i].name, name))
		{
			return i;
		}
	}
	return -1;
}

void AddContact(struct Contact* ps)
{
	assert(ps);
	if (0 == check_capacity(ps))
	{
		return;
	}
	int flag = 1;
	while(flag)
	{
		printf("请输入要添加的名字:> ");
		fgets(ps->data[ps->sz].name, sizeof(ps->data[ps->sz].name), stdin);
		int i = 0;
		while(i < ps->sz)
		{
			if (strcmp(ps->data[i].name, ps->data[ps->sz].name) == 0)
			{
				printf("已有重复联系人, 请重新输入\n");
				break;
			}
			++i;
		}
		flag = 0;
	}

	printf("请输入要添加的性别:>");
	fgets(ps->data[ps->sz].gender, sizeof(ps->data[ps->sz].gender), stdin);
	printf("请输入要添加的地址:>");
	fgets(ps->data[ps->sz].address, sizeof(ps->data[ps->sz].address), stdin);
	printf("请输入要添加的职业:>");
	fgets(ps->data[ps->sz].work, sizeof(ps->data[ps->sz].work), stdin);
	printf("请输入要添加的电话号码:>");
	fgets(ps->data[ps->sz].number, sizeof(ps->data[ps->sz].number), stdin);
    printf("请输入要添加的年龄:>");
    scanf("%d", &ps->data[ps->sz].age);
    ++ps->sz;
    printf("已成功添加第%d个联系人\n", ps->sz);
}

int SpecifyDel(Contact* ps)
{
	assert(ps);
	int j = 0;
	int i = 0;
	int m = 0;
	printf("请输入要删除的代号: >");
	while (scanf("%d", &m) != EOF)
	{
		if (m < 1 || m > ps->sz)
		{
			printf("此代号不存在，请重新输入！\n");
			continue;
		}
		else
			break;
	}
	m -= 1;
	FILE* bin = fopen("E://recycle bin.txt", "a");//文件指针，往回收站文本文件里写入
	if (bin == NULL)
	{
		perror("ZDDel::fopen");
		printf("文件创建失败，请重新进行删除\n");
		return 1;
	}
	fprintf(bin, "姓名：%s  性别：%s  地址：%s  工作：%s  电话：%s  年龄：%d\n", ps->data[m].name, ps->data[m].gender, ps->data[m].address, ps->data[m].work, ps->data[m].number, ps->data[m].age);
	fclose(bin);
	bin = NULL;
	for (j = m; j < ps->sz - 1; j++)
	{
		ps->data[j] = ps->data[j + 1];
	}
	ps->sz--;
	printf("成功删除指定联系人\n");
	return 0;
}

void InsertSort(int* a, int n)
{
	for (int i = 0; i < n - 1; i++)
	{
		int end = i;
		int tmp = a[end + 1];
		while (end >= 0)
		{
			if (tmp > a[end])
			{
				a[end + 1] = a[end];
				end--;
			}
			else
			{
				break;
			}
		}
		a[end + 1] = tmp;
	}
}

int BatchDel(Contact* pb)
{
	assert(pb);
	int num[30] = { 0 };
	int n = 0;
	int m = 1;
	int size = 0;
	printf("请输入要删除的序列号: ");
	printf("选择好后, 输入 666 即可进行批量删除\n");
	while (m && scanf("%d", &n) != EOF)
	{
		if (n == 666)
			break;
		num[size] = n;
		if (size < 30)
			size++;
		if (size == 30)
		{
			printf("最多选择30个, 是否开始删除? 0、是   1、否\n");
			scanf("%d", &m);
			if (m)
			{
				printf("您可以继续选择, 但只会删除前30个\n");
			}
		}
	}
	InsertSort(num, size);
	FILE* bin = fopen("E://recycle bin.txt", "a");
	if (bin == NULL)
	{
		perror("ZDDel::fopen");
		printf("文件创建失败，请重新进行删除\n");
		return 1;
	}
	int x = pb->sz, y = 0, z = 0, ret = 0;
	while (x > 1)
	{
		while (x != num[z])
		{
			x--;
		}
		y = x - 1;
		while ((y + 1) == num[z] && z < size)
		{
			fprintf(bin, "姓名：%s  性别：%s  地址：%s  工作：%s  电话：%s  年龄：%d\n", pb->data[y].name, pb->data[y].gender, pb->data[y].address, pb->data[y].work, pb->data[y].number, pb->data[y].age);
			y--;
			z++;
		}
		y += 1;
		ret = x - y;
		for (int i = y; i < pb->sz - ret; i++)
		{
			pb->data[i] = pb->data[i + ret];
		}
		pb->sz = pb->sz - ret;
		x = y;
	}
	fclose(bin);
	bin = NULL;
	return 0;
}

void DelContact(Contact* ps)
{
	assert(ps);
	int n = 0;
	int s = 1;
	int b = 1;
	int i = 0;
	int num = 0;
	char ret[20] = { '\0' };
	printf("进行删除前请仔细看好每个联系人的代号\n");
	if(!ShowContact(ps)) return ;
	printf("请选择删除模式: 1、指定删除  2、批量删除\n");
	while (scanf("%d", &n) != EOF)
	{
		if (n == 1)
		{
			while (s)
			{
				s = SpecifyDel(ps);
				if (s != 0)
					++num;
				if (3 == num)
				{
					printf("程序自身已出现错误3次, 强制退出, 请重新开始\n");
					s = 0;
					num = 0;
				}
			}
			break;
		}
		else if (n == 2)
		{
			while (b)
			{
				b = BatchDel(ps);
				if (b != 0)
					++num;
				if (3 == num)
				{
					printf("程序自身已出现错误3次, 强制退出, 请重新开始\n");
					b = 0;
					num = 0;
				}
			}
			break;
		}
		else
		{
			printf("请重新输入\n");
			continue;
		}
	}
}

void FindContact(const struct Contact* ps)
{
	assert(ps);
	char name[NAME];
	printf("请输入要查找的名字: >");
	scanf("%s", name);
	int ret = FindByName(name, ps);
	if (ret == -1)
		printf("要查找的人不存在\n");
	else
	{
		printf("%-9s\t%-7s\t%-10s\t%-10s\t%-15s\t%5s\n", "姓名", "性别", "地址", "职业", "号码", "年龄");
		printf("%-9s\t%-7s\t%-10s\t%-10s\t%-15s\t%5d\n", ps->data[ret].name,
			ps->data[ret].gender,
			ps->data[ret].address,
			ps->data[ret].work,
			ps->data[ret].number,
			ps->data[ret].age);
	}
}

void ModifyContact(const struct Contact* ps)
{
	printf("请输入要修改人的名字:>");
	char name[NAME];
	scanf("%s", name);
	int ret = FindByName(name, ps);
	if (ret == -1)
		printf("要修改的人不存在\n");
	else
	{
		printf("请输入要修改的名字:>");
		scanf("%s", ps->data[ret].name);
		printf("请输入要修改的性别:>");
		scanf("%s", ps->data[ret].gender);
		printf("请输入要修改的地址:>");
		scanf("%s", ps->data[ret].address);
		printf("请输入要修改的职业:>");
		scanf("%s", ps->data[ret].work);
		printf("请输入要修改的电话号码:>");
		scanf("%s", ps->data[ret].number);
		printf("请输入要修改的年龄:>");
		scanf("%d", &ps->data[ret].age);
		printf("修改成功\n");
	}
}

bool ShowContact(const struct Contact* ps)
{
	printf("\n");
	if (0 == ps->sz)
	{
		printf("暂无联系人\n");
		return false;
	}
	int i = 0;
	printf("     %-9s\t%-7s\t%-15s\t%-10s\t%-15s\t%-5s\n", "姓名", "性别", "地址", "职业", "号码", "年龄");
	for (i = 0; i < ps->sz; i++)
	{
		printf("%d.   %-9s\t%-7s\t%-15s\t%-10s\t%-15s\t%-5d\n", (i + 1),
			ps->data[i].name,
			ps->data[i].gender,
			ps->data[i].address,
			ps->data[i].work,
			ps->data[i].number,
			ps->data[i].age);
	}
	return true;
}

int CmpByName(const void* q1, const void* q2)
{
	return strcmp(((PeoInfo*)q1)->name, ((PeoInfo*)q2)->name);
}

void SortContact(const struct Contact* ps)
{
	qsort(ps->data, ps->sz, sizeof(struct PeoInfo), CmpByName);
}

void ClassifyContact(const Contact* ps)
{
	int num = 0;
}