# Java 实现 Dijstra 最短路径算法

该仓库提供了一个可以用于查询给定地图信息时最短路径的功能 Jar 包，底层是用 Dijstra 算法实现的。

## 仓库结构

> + **data**：存储 I/O 文件的目录，其中 input 中存放输入文件、output 存放输出文件；
> + **src**：存放着源码文件，最好不要修改。如果只需要应用功能，直接**忽略它**。
> + **Jar**：是应用 Jar 包，配合 data 文件夹使用可以提供给定地图信息的最短路径查询功能。

## 使用说明

使用时注意，你**不需要安装任何开发软件，不需要懂 src 任何代码**。我都封装好了，你所需要做的就是在 data 文件夹里按格式填写好地图信息，然后在打开 CMD，在里面输入一条指令就可以了。下面我具体展示一下怎么使用。

### 1，下载 Jar 包

首先在仓库目录下找到 Jar 包的位置，如下图：

<div align=center>
<img width=700 heigh=300 src="https://github.com/LiePleased/FindShortestPath/blob/master/photo/0.png" />
</div>

然后点击进入并将它 download 下来，如下图：

<div align=center>
<img width=700 heigh=300 src="https://github.com/LiePleased/FindShortestPath/blob/master/photo/1.png" />
</div>

### 2，配置 JVM 环境

去 [Java-SE](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) 官网上下载 JDK（JVM） 安装包，如下图所示：

<div align=center>
<img width=400 heigh=400 src="https://github.com/LiePleased/FindShortestPath/blob/master/photo/4.png" />
</div>

下载安装后，还需要配置环境变量。打开控制面板，在系统变量中设置如下：

> + **JAVA_HOME**：\path\to\jdk（即 JDK 安装的绝对路径）
> + **CLASSPATH**：.;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar;（照抄）
> + **Path**：%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin（增加原有路径）

### 3，写入地图信息

在下载 Jar 包的所在目录新建一个 data 文件夹，再在 data 文件夹中新建 input 文件夹作为输入目录，其内容如下：

<div align=center>
<img width=600 heigh=200 src="https://github.com/LiePleased/FindShortestPath/blob/master/photo/2.png" />
</div>

> + [**node.txt**](https://github.com/LiePleased/FindShortestPath/blob/master/data/input/node.txt)：写入地点信息，包括标识名、经纬度；
> + [**edge.txt**](https://github.com/LiePleased/FindShortestPath/blob/master/data/input/edge.txt)：写入路径约束，即存在直线可达的顶点对；
> + [**query.txt**](https://github.com/LiePleased/FindShortestPath/blob/master/data/input/query.txt)：写入查询需求，由地点的标识名确定；

