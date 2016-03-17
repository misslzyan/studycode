<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>登录页面</title>
<script type="text/javascript" src="static/js/heatmap.min.js"></script>
<script type="text/javascript" src="static/js/prism.js"></script>
<script type="text/javascript" src="static/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
 $(function(){
	console.log(document.getElementById('heatmapContainer'));
	var config = {
			container:document.getElementById('heatmapContainer'),
			radius:10,
			maxOpacity:.5,
			minOpacity:0,
			blur:.75,
			gradient: {
				    // enter n keys between 0 and 1 here
				    // for gradient color customization
				    '.5': 'blue',
				    '.8': 'red',
				    '.95': 'black'
			}
	};
	var heatmapInstance = h337.create(config);
	var dataPoint = { 
			  x: 5, // x coordinate of the datapoint, a number 
			  y: 5, // y coordinate of the datapoint, a number
			  value: 100 // the value at datapoint(x, y)
			};
	heatmapInstance.addData(dataPoint);
	var dataPoint1 = { 
			  x: 50, // x coordinate of the datapoint, a number 
			  y: 50, // y coordinate of the datapoint, a number
			  value: 90 // the value at datapoint(x, y)
			};
	var dataPoint2 = { 
			  x: 800, // x coordinate of the datapoint, a number 
			  y: 400, // y coordinate of the datapoint, a number
			  value: 90 // the value at datapoint(x, y)
			};
	heatmapInstance.addData(dataPoint1);
	heatmapInstance.addData(dataPoint2);
});

</script>

</head>
<body>
<!-- <img  src="static/images/1.jpg" style="width:1000px;height:1000px"> -->
<div id="heatmapContainer" style="width:1000px;height:1000px;background-color:white;background-image:url(static/images/1.jpg)"></div>
<!--<form nam e="loginForm" method="post" action="judgeUser.jsp">
<table>
<tr>
<td>用户名:<input type="text" name="userName" id="userName"></td>
</tr>
<tr>
<td>密码:<input type="password" name="password" id="password"></td>
</tr>
<tr>
<td><input type="submit" value="登录" style="background-color:pink"> <input type="reset" value="重置" style="background-color:red"></td> 
</tr>
</table>
</form> -->
</body>
</html>