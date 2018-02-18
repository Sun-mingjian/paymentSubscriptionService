$(document).ready(function() {

	$('div[name="weekSection"]').hide();
	$('div[name="monthSection"]').hide();

	$('select[name="subscriptionType"]').change(function() {
		if ($(this).val() === "DAILY") {
			$('div[name="weekSection"]').hide();
			$('div[name="monthSection"]').hide();
		} else if ($(this).val() === "WEEKLY") {
			$('div[name="weekSection"]').show();
			$('div[name="monthSection"]').hide();
		} else {
			$('div[name="weekSection"]').hide();
			$('div[name="monthSection"]').show();
		}
	});
	
	$('input[name="submitButton"]').click(function(){
		var amountValue = $('input[name="amountValue"]').val();
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		var now = new Date();
		var maximumEndDate = "";
		var dateOfWeek = $('select[name=dayOfWeek]').val();
		var dateOfMonth = $('input[name="dayOfMonth"]').val();
		var subscriptionType = $('select[name="subscriptionType"]').val();

		if(!validateAmount(amountValue)){
			return;
		}
		
		if(!validateStartDate(startDate, endDate, now)){
			return;
		}else{
			var tempStartDate = new Date(startDate);
			maximumEndDate = tempStartDate.addMonths(3);
		}
		
		if(!validateEndDate(startDate,endDate,maximumEndDate)){
			return;
		}
		
		if(subscriptionType==="MONTHLY"&&!validateDateOfMonth(dateOfMonth, startDate, endDate)){
			return;
		}
		
		if(subscriptionType==="WEEKLY"&&!validateDateOfWeek(dateOfWeek,startDate, endDate)){
			return
		}
		
		
		$('form[name="subscriptionDetailForm"]').submit();
		
	})
});

Date.prototype.addMonths = function (m) {
    var d = new Date(this);
    var years = Math.floor(m / 12);
    var months = m - (years * 12);
    if (years) d.setFullYear(d.getFullYear() + years);
    if (months) d.setMonth(d.getMonth() + months);
    return d;
}


function validateAmount(amountValue){
	if(amountValue<=0){
		$('#invalidAmount').show();
		$('#invalidAmount').html("Opps, amount vaule should be more than 0");
		return false;
	}else{
		$('#invalidAmount').hide();
		return true;
	}
}

function validateStartDate(startDate, endDate, now){
	if(startDate==""){
		$('#invalidStartDate').show();
		$('#invalidStartDate').html("Opps, this field is mandatory");
		return false;
	}else if(Date.parse(startDate)-Date.parse(now)<0){
		$('#invalidStartDate').show();
		$('#invalidStartDate').html("Opps, payment start date should be a future day");
		return false;
	}else{
		$('#invalidStartDate').hide();
		return true;
	}
}

function validateEndDate(startDate,endDate,maximumEndDate){
	if(endDate==""){
		$('#invalidEndDate').html("Opps, this field is mandatory");
		return false;
	}else if(Date.parse(startDate)-Date.parse(endDate)>=0){
		$('#invalidEndDate').html("Opps, payment end date should be after payment start date ");
		$('#invalidEndDate').show();
		return false;
	}else if(maximumEndDate!="" && (maximumEndDate-Date.parse(endDate))<0){
		$('#invalidEndDate').html("Opps, payment end date should not be after "+maximumEndDate.getUTCDate()+"/"+(maximumEndDate.getUTCMonth()+1)+"/"+maximumEndDate.getFullYear()
				+", for a maximum duration of 3 months");
		$('#invalidEndDate').show();
		return false;
	}else{
		$('#invalidEndDate').hide();
		return true;
	}
	
}

function validateDateOfMonth(dateOfMonth, startDate, endDate){
	if(dateOfMonth<1 || dateOfMonth>31){
		$('#invalidDayOfMonth').show().html("Opps, value should be range from 1 to 31");
		return false;
	}else if(!validateDateOfMonthFurther(dateOfMonth, startDate, endDate)){
		return false;
	}else{
		$('#invalidDayOfMonth').hide();
		return true;
	}
}

function validateDateOfMonthFurther(dateOfMonth, startDate, endDate){
	var start = new Date(startDate);
	var end = new Date(endDate);
	while(start<=end){
		if(start.getDate() == dateOfMonth){
			$('#invalidDayOfMonth').hide();
			return true;
		}
		start.setDate(start.getDate()+1);
	}
	$('#invalidDayOfMonth').show().html("Opps, please change the start invoice date or end invoice date since this day of month does not exist in this time range");
	return false;
}

function validateDateOfWeek(dateOfWeek, startDate, endDate){
	var days = ['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday'];
	var start = new Date(startDate);
	var end = new Date(endDate);
	while(start<=end){
		if(start.getDay() == dateOfWeek){
			$('#invalidDayOfWeek').hide();
			return true;
		}
		start.setDate(start.getDate()+1);
	}
	$('#invalidDayOfWeek').show().html("Opps, please change the start invoice date or end invoice date since \'"+days[dateOfWeek]+"\' does not exist in this time range");
	return false;
	
}