<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>my bookstore</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<meta http-equiv="imagetoolbar" content="10款动感图片展示js代码-效果预览(1)">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link media="screen" rel="stylesheet" href="down-menu.css"/>

	<script src="jquery-1.9.1.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	jQuery(document).ready(function(){
		var qcloud={};
		$('[_t_nav]').hover(function(){
			var _nav = $(this).attr('_t_nav');
			clearTimeout( qcloud[ _nav + '_timer' ] );
			qcloud[ _nav + '_timer' ] = setTimeout(function(){
			$('[_t_nav]').each(function(){
			$(this)[ _nav == $(this).attr('_t_nav') ? 'addClass':'removeClass' ]('nav-up-selected');
			});
			$('#'+_nav).stop(true,true).slideDown(200);
			}, 150);
		},function(){
			var _nav = $(this).attr('_t_nav');
			clearTimeout( qcloud[ _nav + '_timer' ] );
			qcloud[ _nav + '_timer' ] = setTimeout(function(){
			$('[_t_nav]').removeClass('nav-up-selected');
			$('#'+_nav).stop(true,true).slideUp(200);
			}, 150);
		});
	});
	</script>
	<style type="text/css">
*{ padding:0; margin:0; list-style:none; border:0;}
.top{
		
		font-size:150px;
		padding-top:20px;
		text-align:center;
		font-color:#fff
		
	}
body{ background-color: #262626; }
.all{ width: 1024px; height: 350px; padding: 7px; border: 1px solid #2D2D2D; margin: 10px auto; position: relative; }
.screen{
	width:1024px;
	height:350px;
	 overflow:hidden; 
	position:relative;
}
.screen li{ width:1024px; height:350px; overflow:hidden; float:left;}
.screen ul{ position:absolute; left:0; top:0px; width:3000px;}
.all ol{ position:absolute; right:10px; bottom:10px; line-height:20px; text-align:center;}
.all ol li{ float: left; width: 15px; height: 15px; background: #fff;  margin-left: 5px; cursor: pointer; font-size: 10px; font-family: Verdana; line-height: 15px; border-radius: 15px; }
.all ol li.current{ background:yellow;}
</style>
<script type="text/javascript">
    window.onload= function() {
        var box  = document.getElementById("all");  //   获得大盒子
        var ul = box.children[0].children[0];  // 获取ul
        var ulLis = ul.children;  //  ul 里面的所有  li
        // 复习：  cloneNode()     克隆节点       追加a.appendChild(b)  把b 放到a 的最后面
        //1.  ulLis[0].cloneNode(true)  克隆  节点
        ul.appendChild(ulLis[0].cloneNode(true));   // ul 是 a   ulLis[0].cloneNode(true) 是b

        //2. 插入 ol 里面的li
        var ol = box.children[1];  // 获得ol
        // 因为 我们要创建很多个 ol 里面的li 所以需要用到for 循环哦
        for(var i=0;i<ulLis.length-1;i++) {   // ul 里面的li  长度 要减去 1  因为我们克隆一个
            // 创建节点 li
            var li = document.createElement("li");
            li.innerHTML = i + 1;   //  存在加1 的关系
            // a.appendChild(b);
            ol.appendChild(li);
        }
        var olLis = ol.children;  // 找到 ol 里面的li
        olLis[0].className = 'current';
        // 3. 动画部分  遍历小li ol
        for(var i=0;i<olLis.length;i++) {
            olLis[i].index = i;  // 赋予索引号
            olLis[i].onmouseover = function() {
                // 清除所有人
                for(var j=0;j<olLis.length;j++) {
                    olLis[j].className = "";
                }
                this.className = 'current';
                animate(ul,-this.index*ulLis[0].offsetWidth);
                key = square = this.index; // 鼠标经过 key square 要等于 当前的索引号
            }
        }
       // 4. 定时器部分  难点
        var timer = null;  // 定时器
        var key = 0; // 用来控制图片的播放的
        var square = 0;  // 用来控制小方块的
        timer = setInterval(autoplay,3000);   // 每隔3s 调用一次 autoplay
        function autoplay() {
            key++;   // key == 1  先 ++
            console.log(key); //  不能超过5
            if(key > ulLis.length - 1)
            {
               // alert('停下');
                ul.style.left = 0;
                key = 1;  // 因为第6张就是第一张，我们已经播放完毕了， 下一次播放第2张
                // 第2张的索引号是1
            }
            animate(ul,-key*ulLis[0].offsetWidth);
            // 小方块的做法
            square++;  // 小方块自加1
            square = square>olLis.length-1 ? 0 : square;
            /// 清除所有人
            for(var i=0;i<olLis.length;i++) {
                olLis[i].className = "";
            }
            olLis[square].className = "current";   // 留下当前自己的

        }


        // 鼠标经过和停止定时器
        box.onmouseover = function() {
            clearInterval(timer);
        }

        box.onmouseout = function() {
            timer = setInterval(autoplay,3000);  // 一定这么开
        }



        //  基本封装
        function animate(obj,target) {
            clearInterval(obj.timer);  // 要开启定时器，先清除以前定时器
            // 有2个参数 第一个 对象谁做动画  第二 距离 到哪里
            // 如果 offsetLeft 大于了  target 目标位置就应该反方向
            var speed = obj.offsetLeft < target ? 15 : -15;
            obj.timer = setInterval(function() {
                var result = target - obj.offsetLeft;  //  他们的值 等于 0 说明完全相等
                // 动画的原理
                obj.style.left = obj.offsetLeft + speed  + "px";
                if(Math.abs(result) <= 15) {
                    obj.style.left = target + "px";   //抖动问题
                    clearInterval(obj.timer);
                }
            },10);
        }

    }
</script>

	
 </head>
  
<body>
     <div  align="center" style="background-color:#27303f;">
			<div  style="font-color:#fff;"> <span style="color:white;font-size:50px">网上银行系统2.0</span></div>
	</div>
  	<!--<div align="center" style="height:120px;">
			<iframe width=100%;height=100%;frameborder="0" src="<c:url value='/jsps/top.jsp'/>" name="top"></iframe>
				
	</div>-->
<div class="head-v3">
	<div class="navigation-up">
		<div class="navigation-inner">
			<div class="navigation-v3">
				<ul>
					<li class="nav-up-selected-inpage" _t_nav="home">
						<h2>
							<!--<a href="http://www.16sucai.com">查询余额</a> -->
							<a href="<c:url value='/user/inquiry1.jsp'/>">查询余额</a>
						</h2>
					</li>
					<li class="" _t_nav="product">
						<h2>
							<a href="<c:url value='/user/deposite1.jsp'/>">存款</a>
						</h2>
					</li>
					<li class="" _t_nav="wechat">
						<h2>
							<a href="<c:url value='/user/withdraw1.jsp'/>">取款</a>
						</h2>
					</li>
					<li class="" _t_nav="solution">
						<h2>
							<a href="<c:url value='/user/transfer1.jsp'/>">转账</a>
						</h2>
					</li>
					<li class="" _t_nav="cooperate">
						<h2>
							<a href="<c:url value='/user/inquiry1.jsp'/>">历史交易记录</a>
						</h2>
					</li>
					<li _t_nav="support">
						<h2>
							<a href="<c:url value='/user/login.jsp'/>">注销</a>
						</h2>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="navigation-down">
		<div id="product" class="nav-down-menu menu-1" style="display: none;" _t_nav="product">
			<div class="navigation-down-inner">
				<dl style="margin-left: 100px;">
					<dt>人民币</dt>
					<dd>
						<a hotrep="hp.header.product.compute1" href="http://www.16sucai.com">本地存取</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.compute2" href="http://www.16sucai.com/?product/product.php?item=cee">异地存取</a>
					</dd>
				</dl>
				<dl>
					<dt>港币</dt>
					<dd>
						<a hotrep="hp.header.product.storage1" href="http://www.16sucai.com/?product/product.php?item=cdb">本地存取</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.storage2" href="http://www.16sucai.com/?product/product.php?item=cmem">异地存取</a>
					</dd>
				</dl>
				<dl>
					<dt>英镑</dt>
					<dd>
						<a hotrep="hp.header.product.monitoring1" href="http://www.16sucai.com/?product/product.php?item=monitor">本地存取</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.monitoring2" href="http://www.16sucai.com/?product/product.php?item=safe">国内存取</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.monitoring3" href="http://www.16sucai.com/?product/product.php?item=cat">异国存取</a>
					</dd>
				</dl>
				<dl>
					<dt>美元</dt>
					<dd>
						<a hotrep="hp.header.product.monitoring1" href="http://www.16sucai.com/?product/product.php?item=monitor">本地存取</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.monitoring2" href="http://www.16sucai.com/?product/product.php?item=safe">国内存取</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.monitoring3" href="http://www.16sucai.com/?product/product.php?item=cat">异国存取</a>
					</dd>
				</dl>
				<dl>
					<dt>欧元</dt>
					<dd>
						<a hotrep="hp.header.product.monitoring1" href="http://www.16sucai.com/?product/product.php?item=monitor">本地存取</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.monitoring2" href="http://www.16sucai.com/?product/product.php?item=safe">国内存取</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.monitoring3" href="http://www.16sucai.com/?product/product.php?item=cat">异国存取</a>
					</dd>
				</dl>
				<dl>
					<dt>泰铢</dt>
					<dd>
						<a hotrep="hp.header.product.monitoring1" href="http://www.16sucai.com/?product/product.php?item=monitor">本地存取</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.monitoring2" href="http://www.16sucai.com/?product/product.php?item=safe">国内存取</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.monitoring3" href="http://www.16sucai.com/?product/product.php?item=cat">异国存取</a>
					</dd>
				</dl>
				<dl>
					<dt>日元</dt>
					<dd>
						<a hotrep="hp.header.product.monitoring1" href="http://www.16sucai.com/?product/product.php?item=monitor">本地存取</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.monitoring2" href="http://www.16sucai.com/?product/product.php?item=safe">国内存取</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.monitoring3" href="http://www.16sucai.com/?product/product.php?item=cat">异国存取</a>
					</dd>
				</dl>
				<dl>
					<dt>韩币</dt>
					<dd>
						<a hotrep="hp.header.product.monitoring1" href="http://www.16sucai.com/?product/product.php?item=monitor">本地存取</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.monitoring2" href="http://www.16sucai.com/?product/product.php?item=safe">国内存取</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.monitoring3" href="http://www.16sucai.com/?product/product.php?item=cat">异国存取</a>
					</dd>
				</dl>
			</div>
		</div>

		<div id="support" class="nav-down-menu menu-3 menu-1" style="display: none;" _t_nav="support">
			<div class="navigation-down-inner">
				<dl style="margin-left: 610px;">
					<dd>
						<a class="link" hotrep="hp.header.support.1" href="http://www.16sucai.com/?wiki.php">用户信息</a>
					</dd>
				</dl>
				<dl>
					<dd>
						<a class="link" hotrep="hp.header.support.2" href="http://bbs.csdn.net/home">退出登录</a>
					</dd>
				</dl>
				<dl>
					<dd>
						<a class="link" hotrep="hp.header.support.3" href="http://www.16sucai.com/?fuchi2014.php">切换用户</a>
					</dd>
				</dl>
			</div>
		</div>
		<div id="cooperate" class="nav-down-menu menu-3 menu-1" style="display: none;" _t_nav="cooperate">
			<div class="navigation-down-inner">
				<dl style="margin-left: 480px;">
					<dd>
						<a hotrep="hp.header.partner.1" href="http://www.16sucai.com/?agent/agent.php">2017</a>
					</dd>
				</dl>
				<dl>
					<dd>
						<a hotrep="hp.header.partner.2" href="http://www.16sucai.com/?apply/apply.php">2016</a>
					</dd>
				</dl>
				<dl>
					<dd>
						<a hotrep="hp.header.partner.3" href="http://www.16sucai.com/?special/venture.php?from=qcloud.banner">2015</a>
					</dd>
				</dl>
				<dl>
					<dd>
						<a hotrep="hp.header.partner.3" href="http://www.16sucai.com/?special/venture.php?from=qcloud.banner">2014</a>
					</dd>
				</dl>
			</div>
		</div>
	</div>
</div>


<div style="text-align:center ">
	<form  action="/Bank3.1/bankdate/item.do?command=withdraw" method="post">
	<br><br><br><br><br><br>
	<label for="pass" style="color:white;font-size:30px">请输入取款金额：</label>
	<br><br><br>
	<input type="text" style="width:370px;height:39px"name="wdrMoney">
	<br><br><br><br><br><br>
	<input type="submit" style="background-color:#262626;color:white;font-size:39px" value="取款">
	</form>
	</div>
</body>
</html>




