<html>

<head>
<title>Subscription Service</title>
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/page.js"></script>
<link href="css/page.css" type="text/css" rel="stylesheet">

</head>

<body>
	<form method="post" name="subscriptionDetailForm">
		<h2>Payment Subscription Details</h2>
		<div>
			Amount : <input name="amountValue" type="number"><errorMsg id="invalidAmount"></errorMsg></input><br> Frequency: <select
				name="subscriptionType">
				<option value="DAILY" selected="selected">Daily</option>
				<option value="WEEKLY">Weekly</option>
				<option value="MONTHLY">Monthly</option>
			</select> <br>
			<div>
				<label for="startDate">Choose your invoice start date:</label> <input
					type="date" id="startDate" name="startPaymentDate"><errorMsg id="invalidStartDate"></errorMsg>
			</div>
			<div>
				<label for="endDate">Choose your invoice end date:</label> <input
					type="date" id="endDate" name="endPaymentDate"><errorMsg id="invalidEndDate"></errorMsg>
			</div>
			<div name="weekSection">
				Day of Week to pay<select name="dayOfWeek">
					<option value="1" selected="selected">Monday</option>
					<option value="2">Tuesday</option>
					<option value="3">Wednesday</option>
					<option value="4">Thursday</option>
					<option value="5">Friday</option>
					<option value="6">Saturday</option>
					<option value="7">Sunday</option>
				</select><errorMsg id="invalidDayOfWeek"></errorMsg>
			</div>
			<div name="monthSection">
				Day of Month to pay<input name="dayOfMonth" type="number" value="1"></input>
				<errorMsg id="invalidDayOfMonth"></errorMsg>
			</div>
			<input type="button" name="submitButton" value="Submit" />
		</div>
	</form>
</body>
</html>