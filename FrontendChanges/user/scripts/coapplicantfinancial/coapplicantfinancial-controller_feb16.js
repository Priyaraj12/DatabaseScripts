var Ps = Ps || {};

(function() {
	'use strict';
	angular
	.module('coApplicantFinancial')

	.controller(
	'coApplicantFinancialController',
	[
	'$state',
	'$http',
	'$scope',
	'getcokkies',
	'config',
	'$rootScope',
	'dropDown',
	'vJson',
	'financialService',
	'getCoApplicantFinancialService',
	'regex',
	'$location',
	function($state, $http, $scope, getcokkies, config,
	$rootScope, dropDown, vJson, financialService,
	getCoApplicantFinancialService, regex,
	$location) {
	
	
	function getCookie(cname) {
	    var name = cname + "=";
	    var decodedCookie = decodeURIComponent(document.cookie);
	    var ca = decodedCookie.split(';');
	    for(var i = 0; i <ca.length; i++) {
	        var c = ca[i];
	        while (c.charAt(0) == ' ') {
	            c = c.substring(1);
	        }
	        if (c.indexOf(name) == 0) {
	            return c.substring(name.length, c.length);
	        }
	    }
	    return "";
	}
	
	var incomedet=[];
	var userLoan=[];
	var finNav=parseInt(localStorage.getItem('PAGE_COMPLETED'));
	$scope.personalInfo = JSON.parse(localStorage.getItem("coApplicantDetails"));

	$rootScope.PAGE_COMPLETED = finNav;
	 $scope.PAGE_COMPLETED = finNav;
	if(finNav >= 5){
	$('#coAppFinNextPage').css('pointer-events', 'auto');
	}
	else{
	$('#coAppFinNextPage').css('pointer-events', 'none');
	}
	dropDown.updateFinancialDropdown();
	
	
	$rootScope.username = localStorage
	.getItem("user_name");
	$rootScope.assetShow = parseInt(localStorage
	.getItem("assetShow"));
	$rootScope.coApplicant = parseInt(localStorage
	.getItem("coApplicant"));
	$rootScope.coApplicantDetails = localStorage
	.getItem("coApplicantDetails");
	$scope.omitdata = [];
	$scope.loader = true;
	$scope.success = false;
	$scope.disableScore = false;

	angular.element(document).ready(function() {
	$('#selector4').trigger('click');
	$(selector).removeClass('active');
	$('#selector2').addClass('active');
	

	});
	
	getCoApplicantFinancialService .getCreditScoreValue() .then(
	function successCallback( response) {
		localStorage.setItem("mint", 600000);
		$rootScope.SessionTime = 600000;
		var t = JSON.parse(response.data.userInfo);
		if (t.mycreditScore != null) {
		if (t.mycreditScore >= 0
		&& t.mycreditScore <= 300) {
		$scope.creditScoreValue = "0-300";
		} else if (t.mycreditScore > 300
		&& t.mycreditScore <= 500) {
		$scope.creditScoreValue = "301-500";
		} else if (t.mycreditScore > 500
		&& t.mycreditScore <= 800) {
		$scope.creditScoreValue = "501-800";
		} else if (t.mycreditScore > 800
		&& t.mycreditScore <= 1000) {
		$scope.creditScoreValue = "801-1000";
		}
		$scope.disableScore = true;
	}
	});

	$scope.incomeChnge = function(e, index) {
	$('#freqIncome-' + index).val('');
	if (e == 1 || e == 2 || e == 4) {
	$('#freqIncome-' + index).val(
	'number:1');
	$('#freqIncome-' + index).attr(
	'disabled', 'disabled');
	} else {
	$('#freqIncome-' + index).val('');
	$('#freqIncome-' + index).removeAttr( 'disabled');
	}

	}

	setFinancialDropdown();
	setFinancialValue();
	$scope.incomeClone = incomeClone;
	$scope.loanClone = loanClone;
	$scope.salaryClone = salaryClone;
	$scope.removeIncome = removeIncome;
	$scope.removeLoan = removeLoan;
	$scope.removeSalary = removeSalary;
	$scope.numericOnly = numericOnly;
	$scope.setSegment = setSegment;
	$scope.removeText = removeText;
	$scope.removeTextTerm = removeTextTerm;
	$scope.reset = reset;
	$scope.removeAllError = removeAllError;
//	$scope.setCreditScore = setCreditScore;
	$scope.linkNext = linkNext;
	$scope.convertNumberToWords = convertNumberToWords;

	$scope.selectedOccupationType = function(id) {
	$scope.skipValues = function(value, index, array) {
	if (id == 4 || id == 5) {
		$(".orgName").addClass('addTop');
		$scope.userInfo.employerName = "";
		$scope.userInfo.yearsofServiceinIndustry = "";
		$scope.userInfo.yearsofServicewithEmployer = "";
		var list = [];
		$scope.IT = $scope.IncomeType;
		$scope.IncomeType = _ .without( $scope.IncomeType, _ .findWhere( $scope.IncomeType, { refIncomeTypeId : 0 }));
		return $scope.IncomeType;

	/* }else if(id == 1){
		$scope.userInfo.occupationcategoryid = "";
		$scope.userInfo.categorydetail = "";
		$(".orgName").removeClass('addTop');
		return $scope.IncomeType; */
	}else {
		$(".orgName").removeClass('addTop');
			return $scope.IncomeType;
		}
	};
	var occId = $.param({
		"OccupationtypeId":id
	});
	
	getCoApplicantFinancialService.getListOfOccupationCategory(occId).then(function successCallback(response){
		$scope.occCatyId = JSON.parse(response.data.Result);
	});
	
	$scope.incomes = [];
	$scope.loanDatas = [];
	$scope.salaryDatas = [];
	$scope.UserSalary = [];
	$scope.UserLoan = [];
	$scope.userIncome = [];
	incomeClone();
	// loanClone();
	
									$scope.userInfo.annualSales = 0;
									$scope.userInfo.annualProfit = 0;
									$scope.userInfo.ownershipShare = 99;
									$scope.userInfo.employeeCount = 0;
									$scope.userInfo.annualSalesPY = 0;
									$scope.userInfo.annualProfitPY = 0;
									$scope.userInfo.officeType = 0;
									$scope.userInfo.businessConstitution = 0;
	}
	
	/**
								 * on change function IT Filed
								 */
								$scope.selectedITFiled = function() {
									
									$scope.userInfo.annualSales = 0;
									$scope.userInfo.annualProfit = 0;
									$scope.userInfo.annualSalesPY = 0;
									$scope.userInfo.annualProfitPY = 0;
									
								}

	$scope.removetextexp = function(val) {
		return val.replace(/[^0-9\/]+/g, "");
	}

	$scope.numericOnly = function(event, val) {
	// skip for arrow keys
	if (event.which >= 37 && event.which <= 40) {
	event.preventDefault();
	}
	var $this = $(this);
	var num = val.replace(/,/gi, "").split("")
	.reverse().join("");

	var num2 = RemoveRougeChar(num.replace(
	/[^0-9]+/g, '').replace(/(.{3})/g,
	"$1,").split("").reverse().join(""));
	return num2;
	};
	// start commas function
	String.prototype.replaceAll = function(search,
	replacement) {
	var target = this;
	return target.replace(new RegExp(search,
	'g'), replacement);
	};

	$scope.commaSeparatedNumeric = function(event,
	val) {
	if (val != undefined) {
	var input = val.replaceAll(',', '');
	if (input.length < 1) {
	$(this).val('0.00');
	} else {
	var val = parseFloat(input);
	var formatted = inrFormat(input);
	if (formatted.indexOf('.') > 0) {
	var split = formatted
	.split('.');
	formatted = split[0]
	+ '.'
	+ split[1].substring(0,
	2);
	}
	return formatted;
	}
	}
	return val;
	};

	function inrFormat(val) {
	var x = val;
	x = x.toString();
	var afterPoint = '';
	if (x.indexOf('.') > 0)
	afterPoint = x.substring(
	x.indexOf('.'), x.length);
	x = Math.floor(x);
	x = x.toString();
	var lastThree = x.substring(x.length - 3);
	var otherNumbers = x.substring(0,
	x.length - 3);
	if (otherNumbers != '')
	lastThree = ',' + lastThree;
	var res = otherNumbers.replace(
	/\B(?=(\d{2})+(?!\d))/g, ",")
	+ lastThree + afterPoint;
	return res;
	}
	// end commas functon

	$scope.convertNumberToWords = function(amount) {
	var words = new Array();
	words[0] = '';
	words[1] = 'One';
	words[2] = 'Two';
	words[3] = 'Three';
	words[4] = 'Four';
	words[5] = 'Five';
	words[6] = 'Six';
	words[7] = 'Seven';
	words[8] = 'Eight';
	words[9] = 'Nine';
	words[10] = 'Ten';
	words[11] = 'Eleven';
	words[12] = 'Twelve';
	words[13] = 'Thirteen';
	words[14] = 'Fourteen';
	words[15] = 'Fifteen';
	words[16] = 'Sixteen';
	words[17] = 'Seventeen';
	words[18] = 'Eighteen';
	words[19] = 'Nineteen';
	words[20] = 'Twenty';
	words[30] = 'Thirty';
	words[40] = 'Forty';
	words[50] = 'Fifty';
	words[60] = 'Sixty';
	words[70] = 'Seventy';
	words[80] = 'Eighty';
	words[90] = 'Ninety';
	amount = amount.toString();
	var atemp = amount.split(".");
	var number = atemp[0].split(",").join("");
	var n_length = number.length;
	var words_string = "";
	if (n_length <= 9) {
	var n_array = new Array(0, 0, 0, 0, 0,
	0, 0, 0, 0);
	var received_n_array = new Array();
	for (var i = 0; i < n_length; i++) {
	received_n_array[i] = number
	.substr(i, 1);
	}
	for (var i = 9 - n_length, j = 0; i < 9; i++, j++) {
	n_array[i] = received_n_array[j];
	}
	for (var i = 0, j = 1; i < 9; i++, j++) {
	if (i == 0 || i == 2 || i == 4
	|| i == 7) {
	if (n_array[i] == 1) {
	n_array[j] = 10 + parseInt(n_array[j]);
	n_array[i] = 0;
	}
	}
	}
	var value = "";
	for (var i = 0; i < 9; i++) {
	if (i == 0 || i == 2 || i == 4
	|| i == 7) {
	value = n_array[i] * 10;
	} else {
	value = n_array[i];
	}
	if (value != 0) {
	words_string += words[value]
	+ " ";
	}
	if ((i == 1 && value != 0)
	|| (i == 0 && value != 0 && n_array[i + 1] == 0)) {
	words_string += "Crores ";
	}
	if ((i == 3 && value != 0)
	|| (i == 2 && value != 0 && n_array[i + 1] == 0)) {
	words_string += "Lakhs ";
	}
	if ((i == 5 && value != 0)
	|| (i == 4 && value != 0 && n_array[i + 1] == 0)) {
	words_string += "Thousand ";
	}
	if (i == 6
	&& value != 0
	&& (n_array[i + 1] != 0 && n_array[i + 2] != 0)) {
	words_string += "Hundred and ";
	} else if (i == 6 && value != 0) {
	words_string += "Hundred ";
	}
	}
	words_string = words_string.split("  ")
	.join(" ");
	}
	return words_string;
	}

	/* Methods to set defaukt page values onload*/ 
	function setFinancialDropdown() {
	$scope.dropdown = JSON.parse(localStorage
	.getItem('dropdown'));
	$scope.occupationTypes = JSON
	.parse($scope.dropdown.OccupationType);
	$scope.ListOfCreditScore = JSON
	.parse($scope.dropdown.CreditScore);
	$scope.IncomeType = JSON
	.parse($scope.dropdown.IncomeType);

	$scope.frequencyType = JSON
	.parse($scope.dropdown.frequencyType);
	$scope.LoanLenderType = JSON
	.parse($scope.dropdown.LoanLenderType);
	$scope.LoanType = JSON
	.parse($scope.dropdown.LoanType);
	$scope.IndustrySector = JSON
	.parse($scope.dropdown.IndustrySector);
	$scope.EmployerName = JSON
	.parse($scope.dropdown.EmployerName);
	$scope.SalaryDeduction = JSON
	.parse($scope.dropdown.SalaryDeduction);
	$scope.businessConstitutionList =
										[ 
											{ name: 'Proprietorship', id: 1 },  
											{ name: 'Pvt. Ltd.', id: 2 },  
											{ name: 'Partnership', id: 3 },
											{ name: 'One Person Company', id: 4 },
											{ name: 'Limited Company', id: 5 },
											{ name: 'Limited Liability Partnership (LLP)', id: 6 },
											{ name: 'Other', id: 7 },
											{ name: 'Not Registered', id: 8 },
										];
									$scope.employeeCountList =
										[ 
											{ name: '1-10', id: 1 },  
											{ name: '11-50', id: 2 },  
											{ name: '51 - 100', id: 3 },
											{ name: '101 - 1000', id: 4 },
											{ name: '1001 - 5000', id: 5 },
											{ name: 'More than 5000', id: 6 }
										];
									$scope.annualSalesList =
										[ 
											{ name: '0 - 3 Lacs', id: 1 },  
											{ name: '3 - 10 Lacs', id: 2 },  
											{ name: '10 - 25 Lacs', id: 3 },
											{ name: '25 - 50 Lacs', id: 4 },
											{ name: '50 Lacs - 1 Crore', id: 5 },
											{ name: '1 - 10 Crore', id: 6 },
											{ name: 'More than 10 Crore', id: 7 }
										];
									$scope.annualProfitList =
										[ 
											{ name: 'No Profit - Net Loss', id: 1 },  
											{ name: 'Less than 3 Lacs', id: 2 },  
											{ name: '3 - 10 Lacs', id: 3 },  
											{ name: '10 - 25 Lacs', id: 4 },
											{ name: '25 - 50 Lacs', id: 5 },
											{ name: '50 Lacs - 1 Crore', id: 6 },
											{ name: '1 - 10 Crore', id: 7 },
											{ name: 'More than 10 Crore', id: 8 }
										];
									$scope.officeTypeList =
										[ 
											{ name: 'Owned', id: 1 },  
											{ name: 'Rented / Leased', id: 2 },  
											{ name: 'Shared / Co-Workspace', id: 3 },  
											{ name: 'Work from Home', id: 4 },
  											{ name: 'No Office Needed', id: 5 } 											
										];
										$scope.primaryBankList =
										[ 
										{ name: 'I don’t have a bank account', id: 999},
{ name: 'Abu Dhabi Commercial Bank', id: 197},
{ name: 'Allahabad Bank', id: 148},
{ name: 'Andhra Bank', id: 149},
{ name: 'Axis Bank', id: 180},
{ name: 'Bandan Bank', id: 196},
{ name: 'Bank of Baroda', id: 150},
{ name: 'Bank of India', id: 151},
{ name: 'Bank of Maharashtra', id: 152},
{ name: 'Barclays Bank', id: 187},
{ name: 'Canara Bank', id: 153},
{ name: 'Catholic Syrian Bank', id: 168},
{ name: 'Central Bank of India', id: 154},
{ name: 'Citibank', id: 189},
{ name: 'City Union Bank', id: 169},
{ name: 'Corporation Bank', id: 155},
{ name: 'DBS Bank', id: 190},
{ name: 'Dena Bank', id: 156},
{ name: 'Deutsche Bank', id: 191},
{ name: 'Development Credit Bank', id: 181},
{ name: 'Dhanlaxmi Bank', id: 170},
{ name: 'Equitas Bank', id: 236},
{ name: 'Federal Bank', id: 171},
{ name: 'Fin Growth Co-Op bank Ltd', id: 199},
{ name: 'HDFC Bank', id: 182},
{ name: 'HSBC', id: 192},
{ name: 'ICICI Bank', id: 183},
{ name: 'IDBI Bank Ltd.', id: 157},
{ name: 'IDFC Bank', id: 195},
{ name: 'Indian Bank', id: 158},
{ name: 'Indian Overseas Bank', id: 159},
{ name: 'IndusInd Bank', id: 184},
{ name: 'Jammu & Kashmir Bank', id: 172},
{ name: 'Karnataka Bank', id: 173},
{ name: 'Karur Vysya Bank', id: 174},
{ name: 'Kotak Mahindra Bank', id: 185},
{ name: 'Lakshmi Vilas Bank', id: 175},
{ name: 'Nainital Bank', id: 176},
{ name: 'Oriental Bank of Commerce', id: 160},
{ name: 'Punjab and Sind Bank', id: 161},
{ name: 'Punjab National Bank', id: 162},
{ name: 'Ratnakar Bank', id: 177},
{ name: 'RBL Bank', id: 237},
{ name: 'Royal Bank of Scotland', id: 193},
{ name: 'Saraswat Bank', id: 200},
{ name: 'South Indian Bank', id: 178},
{ name: 'Standard Chartered Bank', id: 194},
{ name: 'State Bank of Bikaner & Jaipur', id: 143},
{ name: 'State Bank of Hyderabad', id: 144},
{ name: 'State Bank of India', id: 142},
{ name: 'State Bank of Mysore', id: 145},
{ name: 'State Bank of Patiala', id: 146},
{ name: 'State Bank of Travancore', id: 147},
{ name: 'Syndicate Bank', id: 163},
{ name: 'Tamilnad Mercantile Bank', id: 179},
{ name: 'UCO Bank', id: 164},
{ name: 'Union Bank of India', id: 165},
{ name: 'United Bank of India', id: 166},
{ name: 'Vijaya Bank', id: 167},
{ name: 'Yes Bank', id: 186},
{ name: 'Others', id: 235}
											];	
	$scope.destArray = _.uniq(
	$scope.EmployerName, function(x) {
	});
	angular
	.forEach(
	$scope.IncomeType,
	function(value, key) {
	$scope.IncomeType[key].disabled = false;
	});
	}
	;

	function setFinancialValue() {
	$scope.incomes = [];
	$scope.loanDatas = [];
	$scope.salaryDatas = [];
	$scope.UserSalary = [];
	$scope.UserLoan = [];
	$scope.userIncome = [];
	$scope.loader = true;
	
	
	getCoApplicantFinancialService.getCoApplicantId().then(function successCallback(response) {
	                    	localStorage.setItem("mint", 600000);
	                    	$rootScope.SessionTime = 600000;
	                        $scope.result = JSON.parse(response.data.result)['#result-set-1'];
	                       var coappliID = $scope.result[0].userid;
	                        
	                        document.cookie = "coAppId="+coappliID;
	                        if ($scope.result.length == 0) {

	                        } else {
	                            var data = $.param({ 'listOfUserId': JSON.stringify($scope.result) });
	                            getCoApplicantFinancialService.getCoApplicantEmploymentAndIncomeLoan(data).then(function successCallback(coApplicantFinancialres) {
									localStorage.setItem("mint", 600000);
	                            	$rootScope.SessionTime = 600000;
	                                
									$scope.coApplicantFinancials = JSON.parse(coApplicantFinancialres.data.Result);
	                                $scope.userInfo = $scope.coApplicantFinancials[0];
									
									$scope.userInfo.itfiled = $scope.userInfo.itfiled ? $scope.userInfo.itfiled : 0;
									$scope.userInfo.primaryBank = $scope.userInfo.primaryBank == null || $scope.userInfo.primaryBank == undefined ? 0 : $scope.userInfo.primaryBank;
																		
	                                $scope.incomes = [];
									$scope.loanDatas = [];
									$scope.salaryDatas = [];
									$scope.UserSalary = [];
									$scope.UserLoan = [];
									$scope.userIncome = [];
									
									/* Let there be no default occupation in co-applicant page
									if($scope.userInfo.occupationTypeId == 0 )
									{
										$scope.userInfo.occupationTypeId = 1;
																				
									}
									*/
									
									
									$scope.userInfo.annualSales = $scope.userInfo.annualSales == null || $scope.userInfo.annualSales == undefined ? 0 : $scope.userInfo.annualSales;
									$scope.userInfo.annualProfit = $scope.userInfo.annualProfit == null || $scope.userInfo.annualProfit == undefined ? 0 : $scope.userInfo.annualProfit;
									$scope.userInfo.ownershipShare = $scope.userInfo.ownershipShare == null || $scope.userInfo.ownershipShare == undefined ? 99 : $scope.userInfo.ownershipShare;
									$scope.userInfo.employeeCount = $scope.userInfo.employeeCount == null || $scope.userInfo.employeeCount == undefined ? 0 : $scope.userInfo.employeeCount;
									$scope.userInfo.annualSalesPY = $scope.userInfo.annualSalesPY == null || $scope.userInfo.annualSalesPY == undefined ? 0 : $scope.userInfo.annualSalesPY;
									$scope.userInfo.annualProfitPY = $scope.userInfo.annualProfitPY == null || $scope.userInfo.annualProfitPY == undefined ? 0 : $scope.userInfo.annualProfitPY;
									$scope.userInfo.officeType = $scope.userInfo.officeType == null || $scope.userInfo.officeType == undefined ? 0 : $scope.userInfo.officeType;
									$scope.userInfo.businessConstitution = $scope.userInfo.businessConstitution == null || $scope.userInfo.businessConstitution == undefined ? 0 : $scope.userInfo.businessConstitution;
									
									
									var catId = $scope.userInfo.occupationcategoryid;
									
									var occId = $.param({
										"OccupationtypeId":$scope.userInfo.occupationTypeId
									});
									
									financialService.getListOfOccupationCategory(occId).then(function successCallback(response){
										$scope.occCatyId = JSON.parse(response.data.Result);
										getCatLabel(catId,"");
									});		
									
									 if($scope.userInfo.occupationTypeId == 1){
										 $scope.incomeTypefltr = [];
										 
										 for(var id in $scope.IncomeType){
												if($scope.IncomeType[id].occupationTypeId == 1){
													$scope.incomeTypefltr.push($scope.IncomeType[id]);
												}
											}
										 }else if($scope.userInfo.occupationTypeId == 2 ){
										 $scope.incomeTypefltr = [];
										 
										 console.log($scope.IncomeType);
										 for(var id in $scope.IncomeType){
												//if($scope.IncomeType[id].occupationTypeId == 2 && $scope.IncomeType[id].occupation_CategoryId == 0 || $scope.IncomeType[id].occupation_CategoryId == 1 || $scope.IncomeType[id].occupation_CategoryId == 2 || $scope.IncomeType[id].occupation_CategoryId == 3 || $scope.IncomeType[id].occupation_CategoryId == 4 || $scope.IncomeType[id].occupation_CategoryId == 5 || $scope.IncomeType[id].occupation_CategoryId == 6 || $scope.IncomeType[id].occupation_CategoryId == 7 || $scope.IncomeType[id].occupation_CategoryId == 8){
											 if(($scope.IncomeType[id].occupationTypeId == 2) 
											 && ($scope.IncomeType[id].refIncomeTypeId == 8  ||
												$scope.IncomeType[id].refIncomeTypeId == 9  ||
												$scope.IncomeType[id].refIncomeTypeId == 1 ||
												$scope.IncomeType[id].refIncomeTypeId == 2 || 
												$scope.IncomeType[id].refIncomeTypeId == 3 || 
												$scope.IncomeType[id].refIncomeTypeId == 4 || 
												$scope.IncomeType[id].refIncomeTypeId == 5 || 
												$scope.IncomeType[id].refIncomeTypeId == 96)){
												 
													$scope.incomeTypefltr.push($scope.IncomeType[id]);

												}
											}
									 }else if($scope.userInfo.occupationTypeId == 3){
										 $scope.incomeTypefltr = [];
								//		$scope.incomeTypefltr.push($scope.IncomeType[10]);
								//		$scope.incomeTypefltr.push($scope.IncomeType[9]);
								//		$scope.incomeTypefltr.push($scope.IncomeType[11]);
								//		$scope.incomeTypefltr.push($scope.IncomeType[12]);
										$scope.incomeTypefltr.push($scope.IncomeType[0]);
										$scope.incomeTypefltr.push($scope.IncomeType[1]);
										$scope.incomeTypefltr.push($scope.IncomeType[2]);
										$scope.incomeTypefltr.push($scope.IncomeType[3]);
										$scope.incomeTypefltr.push($scope.IncomeType[4]);
										$scope.incomeTypefltr.push($scope.IncomeType[6]);
								//		$scope.incomeTypefltr.push($scope.IncomeType[14]);
								//		$scope.incomeTypefltr.push($scope.IncomeType[13]);

									 }else if($scope.userInfo.occupationTypeId == 4){
										 $scope.incomeTypefltr = [];
										 $scope.incomeTypefltr.push($scope.IncomeType[1]);
										 $scope.incomeTypefltr.push($scope.IncomeType[3]);
										 $scope.incomeTypefltr.push($scope.IncomeType[4]);
										 $scope.incomeTypefltr.push($scope.IncomeType[2]);
									 }else if($scope.userInfo.occupationTypeId == 5){
										 $scope.incomeTypefltr = [];
										 $scope.incomeTypefltr.push($scope.IncomeType[3]);
										 $scope.incomeTypefltr.push($scope.IncomeType[1]);
										 $scope.incomeTypefltr.push($scope.IncomeType[4]);
										 $scope.incomeTypefltr.push($scope.IncomeType[2]);
									 }

									 var tempIncome = [];
									 var temp;									
	                                for (var i = 0; i < $scope.coApplicantFinancials[0].userIncome.length; i++) {
	                                	if(tempIncome.length != 0){
											$scope.incomeTypefltr = tempIncome;
										}
										for(var x in $scope.incomeTypefltr){												
											if($scope.coApplicantFinancials[0].userIncome[i]['incomeTypedesc'] == $scope.incomeTypefltr[x]['incomeTypedesc']){
												temp = $scope.incomeTypefltr[x];
												tempIncome = [];
												for(var y in $scope.incomeTypefltr){
													if(temp['incomeTypedesc'] !== $scope.incomeTypefltr[y]['incomeTypedesc']){
														tempIncome.push($scope.incomeTypefltr[y]);
													}														
												}												
											}
										}
	                                    var incomeData = {
	                                        "userIncomeId": $scope.coApplicantFinancials[0].userIncomeId ? $scope.coApplicantFinancials[0].userIncomeId : 0,
	                                        "activeStatus": "",
	                                        "createdBy": "",
	                                        "createdOn": "",
	                                        "updatedBy": "",
	                                        "updatedOn": "", 
	                                        "userGrossIncome": 0,
	                                        "userNetIncome": $scope.coApplicantFinancials[0].userIncome[i].userNetIncome.toLocaleString('en-IN'),
	                                        "frequencyType" :  $scope.coApplicantFinancials[0].userIncome[i].frequencyType,
	                                        "userMonthlySavings": 0,
	                                        "refIncomeTypeId": $scope.coApplicantFinancials[0].userIncome[i].refIncomeTypeId,
	                                        "IncomeType": $scope.incomeTypefltr
	                                    };
	                                   
	                                    $scope.incomes.push(incomeData);
	                                }
	                                $scope.loanDatas = [];
	                                for (var i = 0; i < $scope.coApplicantFinancials[0].userLoan.length; i++) {
	                                    var loanData = {
	                                        "userLoansId": $scope.coApplicantFinancials[0].userLoan[i].userLoansId ? $scope.coApplicantFinancials[0].userLoan[i].userLoansId : 0,
	                                        "activeStatus": "",
	                                        "createdBy": "",
	                                        "createdOn": "",
	                                        "loanTypeId": $scope.coApplicantFinancials[0].userLoan[i].loanTypeId,
	                                        "manualEntry": "",
	                                        "updatedBy": "",
	                                        "updatedOn": "",
	                                        "userId": "",
	                                        "userLenderName": $scope.coApplicantFinancials[0].userLoan[i].userLenderName,
	                                        "userLoanOutstandingPrincipal": $scope.coApplicantFinancials[0].userLoan[i].userLoanOutstandingPrincipal,
	                                        "userLoanPaymentAmount": $scope.coApplicantFinancials[0].userLoan[i].userLoanPaymentAmount.toLocaleString('en-IN'),
	                                        "userLoanPeriod": "",
	                                        "userLoanRemainingTenure": $scope.coApplicantFinancials[0].userLoan[i].userLoanRemainingTenure
	                                    };
	                                    $scope.loanDatas.push(loanData);
	                                }
	                                
	                                
	                                $scope.loader = false;
	                            }, function errorCallback(response) {});
	                        }
	                    }, function errorCallback(response) {});

	if ($scope.userIncome.length > 0) {
	for (var i = 0; i < $scope.userIncome.length; i++) {

	var incomeData = {
	"userIncomeId" : $scope.userIncome[i].userIncomeId ? $scope.userIncome[i].userIncomeId: 0,
	"activeStatus" : "",
	"createdBy" : "",
	"createdOn" : "",
	"updatedBy" : "",
	"updatedOn" : "",
	"userGrossIncome" : 0,
	"userNetIncome" : $scope.userIncome[i].userNetIncome == null ? 0
	: $scope.userIncome[i].userNetIncome
	.toLocaleString('en-IN'),
	"frequencyType" : $scope.userIncome[i].frequencyType == null ? 0
	: $scope.userIncome[i].frequencyType,
	"userMonthlySavings" : 0,
	"refIncomeTypeId" : $scope.userIncome[i].refIncomeTypeId ? $scope.userIncome[i].refIncomeTypeId
	: 0,
	"IncomeType" : $scope.IncomeType
	};

	$scope.incomes.push(incomeData);
	}
	$scope.incomes_length = $scope.incomes.length;
	}
	;
	if ($scope.UserLoan.length > 0) {
	for (var i = 0; i < $scope.UserLoan.length; i++) {
	var loanData = {
	"userLoansId" : $scope.UserLoan[i].userLoansId ? $scope.UserLoan[i].userLoansId: 0,
	"activeStatus" : "",
	"createdBy" : "",
	"createdOn" : "",
	"loanTypeId" : $scope.UserLoan[i].loanTypeId,
	"manualEntry" : "",
	"updatedBy" : "",
	"updatedOn" : "",
	"userId" : "",
	"userLenderName" : $scope.UserLoan[i].userLenderName,
	"userLoanOutstandingPrincipal" : $scope.UserLoan[i].userLoanOutstandingPrincipal,
	"userLoanPaymentAmount" : $scope.UserLoan[i].userLoanPaymentAmount.toLocaleString('en-IN'),
	"userLoanPeriod" : "",
	"userLoanRemainingTenure" : $scope.UserLoan[i].userLoanRemainingTenure
	};
	$scope.loanDatas.push(loanData);
	}
	$scope.userloan_length = $scope.loanDatas.length;
	}
	;
	if ($scope.loanDatas.length == 0) {
	$scope.currentObligation = 0;
	} else {
	$scope.currentObligation = 1;
	}
	if ($scope.UserSalary.length > 0) {
	for (var i = 0; i < $scope.UserSalary.length; i++) {
	var salaryData = {
	"usersaldednsId" : $scope.UserSalary[i].usersaldednsId ? $scope.UserSalary[i].usersaldednsId : 8,
	"activeStatus" : "",
	"createdBy" : "",
	"createdOn" : "",
	"updatedBy" : "",
	"updatedOn" : "",
	"amount" : $scope.UserSalary[i].amount.toLocaleString(),
	"salaryDednTypeId" : $scope.UserSalary[i].salaryDednTypeId ? $scope.UserSalary[i].salaryDednTypeId
	: 8,
	};
	$scope.salaryDatas.push(salaryData);
	}
	$scope.salaryDatas_length = $scope.salaryDatas.length;

	} ;
	} ;

	function setSegment(segment) {
	var objectSegment = _.findWhere(
	$scope.EmployerName, {
	employerName : segment
	});
	if (segment == null) {
	$("#organvalid").show();
	}
	}
	;
	$scope.validOrNotValidBlur = function(val) {
	if (val == undefined || val == 0) {
	$(".totalExpValid").show();
	
	}
	}
	$scope.validOrNotValidBlur1 = function(val) {
	if (val == undefined || val == 0) {
	$(".totalExpValid1").show();
	}
	}
	$scope.$watch('financialformditry.$dirty',
	function(neeval, oldval) {
	if (neeval == true) {
	$scope.true_reset = true;
	$scope.success = false;
	$scope.error = false;
	} else {
	$scope.true_reset = false;
	}
	}, true);

				function reset() {
					$scope.loader = true;
					$scope.success_message = "Your changes have been successfully reset";
					$scope.removeAllError();
					$scope.financialformditry.$setPristine(true);
					$scope.success = true;
					$scope.error = false;
					setFinancialValue();
					$scope.loader = false;
					$scope.true_reset = false;
				};

				function removeAllError() {
				_.each($scope.omitdata, function(value) {
					$('#' + value).removeClass('redcolorinput');
				})
				_.each($scope.checkemptydata, function(value) {
					$('#' + value).removeClass('redcolorinput');
				})
				_.each($scope.emptyincome, function(value) {
					$('#income' + value).removeClass('redcolorinput');
				})
				_.each($scope.Tenure, function(value) {
					$('#Tenure' + value).removeClass('redcolorinput');
				})
				_.each($scope.Principal, function(value) {
					$('#Principal' + value).removeClass('redcolorinput');
				})
				_
				.each($scope.PaymentAmount, function(value) {
					$('#PaymentAmount'+value).removeClass('redcolorinput');
				})
			
				$scope.checkemptydata = [];
				$scope.omitdata = [];
				$scope.emptyincome = [];
				$scope.Tenure = [];
				$scope.Principal = [];
				$scope.PaymentAmount = [];
			};						

/* Prernna commented setCreditScore for cleanup
			function setCreditScore(val) {
				if (val <= 550) {
					$scope.userInfo.refCreditScoreId = 4;
					$('#myModal').modal('hide');
				} else if (val >= 551 && val <= 650) {
					$scope.userInfo.refCreditScoreId = 3;
					$('#myModal').modal('hide');
				} else if (val >= 651 && val <= 750) {
					$scope.userInfo.refCreditScoreId = 2;
					$('#myModal').modal('hide');
				} else if (val >= 751 && val <= 999) {
					$scope.userInfo.refCreditScoreId = 1;
					$('#myModal').modal('hide');
				}
			};
ending comment */ 

			function linkNext() {
				if($rootScope.coApplicant == 1) {
					$state.go('index.coApplicant');
				} else {
					//$state.go('index.bankverification');
					$state.go('index.credit');
				}
			};

			/* Clone */
			function incomeClone(e,ctg) {
				console.log("$scope.incomes",$scope.IncomeType);
				var plusclk;
				var occType = $("#occupationType option:selected").text();
				if(occType == undefined || occType == "" || occType == null  && finNav == 3) {
					 occType = "Salaried";
				}else{
					 occType = $("#occupationType option:selected").text();
				}
				
				var ee = e == undefined ? undefined : e.type; 
				if(ee == "click"){
					e = 'ctgyChange';
					ctg = $scope.userInfo.occupationcategoryid;
					plusclk = "plusClick"
					
				}
			if (occType == "Home Maker") { //Home Maker
			var dropdown = [];
			dropdown.push($scope.IncomeType[1]);
			dropdown.push($scope.IncomeType[3]);
			dropdown.push($scope.IncomeType[4]);
			dropdown.push($scope.IncomeType[2]);
			$scope.IT = dropdown;
			}else if(occType == "Retired"){ // Retired
			var dropdown = [];
			dropdown.push($scope.IncomeType[3]);
			dropdown.push($scope.IncomeType[1]);
			dropdown.push($scope.IncomeType[4]);
			dropdown.push($scope.IncomeType[2]);
			$scope.IT = dropdown;
			} else if (occType == "Salaried"){  //salaried
				var dropdown = [];
				dropdown.push($scope.IncomeType[0]);
				$scope.refId = $scope.IncomeType[0]['refIncomeTypeId'];
				dropdown.push($scope.IncomeType[1]);
				dropdown.push($scope.IncomeType[2]);
				dropdown.push($scope.IncomeType[3]);
				dropdown.push($scope.IncomeType[4]);
				dropdown.push($scope.IncomeType[5]);
				dropdown.push($scope.IncomeType[6]);
				$scope.IT =dropdown ;
				console.log("IncomeType",$scope.IT)
			} else if (occType == "Self Employed Professional") {	//Self Employed Professional
				
				var dropdown = [];
					$scope.IT = [];
						dropdown.push($scope.IncomeType[7]);
						dropdown.push($scope.IncomeType[8]);
						dropdown.push($scope.IncomeType[0]);
						dropdown.push($scope.IncomeType[1]);
						dropdown.push($scope.IncomeType[2]);
						dropdown.push($scope.IncomeType[3]);
						dropdown.push($scope.IncomeType[4]);
						e = undefined;
						$scope.IT = dropdown;
				

			} else { 								// Business Owners
				var dropdown = [];
				dropdown.push($scope.IncomeType[0]);
				$scope.refId = $scope.IncomeType[0]['refIncomeTypeId'];
				dropdown.push($scope.IncomeType[1]);
				dropdown.push($scope.IncomeType[2]);
				dropdown.push($scope.IncomeType[3]);
				dropdown.push($scope.IncomeType[4]);
				dropdown.push($scope.IncomeType[6]);
				$scope.IT =dropdown ;
				
				
			}
				_.each( $scope.incomes, function(value) {
							var ii = _.findIndex($scope.incomes,value);
							$scope.incomes[ii].disabled = true;
							$scope.IT = _.without($scope.IT,_.findWhere($scope.IT,
							{
							refIncomeTypeId : value.refIncomeTypeId
							}));
					});
				
				if($scope.IT[0].refIncomeTypeId == 8 || $scope.IT[0].refIncomeTypeId == 9 ||$scope.IT[0].refIncomeTypeId == 10 || $scope.IT[0].refIncomeTypeId == 11 || $scope.IT[0].refIncomeTypeId == 12 || $scope.IT[0].refIncomeTypeId == 13 || $scope.IT[0].refIncomeTypeId == 14 || $scope.IT[0].refIncomeTypeId == 15 ){
					$scope.fTyp = 12;
				}else if($scope.IT[0].refIncomeTypeId == 1 || $scope.IT[0].refIncomeTypeId == 2 || $scope.IT[0].refIncomeTypeId == 4 ||  $scope.IT[0].refIncomeTypeId == 115 || $scope.IT[0].refIncomeTypeId == 116 || $scope.IT[0].refIncomeTypeId == 91 || $scope.IT[0].refIncomeTypeId == 92){
					$scope.fTyp = 1;
				}else {
					$scope.fTyp = 1;
				}
				
				if(e == undefined){
				
					if(occType == "Salaried" || occType == "Home Maker" || occType == "Retired"){
							
							var incomeData = {
									"userIncomeId" : 0,
									"activeStatus" : "",
									"createdBy" : "",
									"createdOn" : "",
									"updatedBy" : "",
									"updatedOn" : "",
									"userGrossIncome" : 0,
									"frequencyType" : $scope.fTyp,
									"userNetIncome" : 0,
									"userMonthlySavings" : 0,
									"refIncomeTypeId" : $scope.IT[0].refIncomeTypeId ?  $scope.IT[0].refIncomeTypeId : $scope.refId,
									"IncomeType" : $scope.IT,
									"disabled" : false
								};
							$scope.incomes.push(incomeData);
						
					}else if(occType == "Self Employed Professional"){
					var plus;
					if(plusclk == 'plusClick'){
						plus = 1;
					}else{
						plus = 2
					}
						for(var u = 0;u<plus;u++){
							
							var incomeData = {
									"userIncomeId" : 0,
									"activeStatus" : "",
									"createdBy" : "",
									"createdOn" : "",
									"updatedBy" : "",
									"updatedOn" : "",
									"userGrossIncome" : 0,
									"frequencyType" : $scope.fTyp,
									"userNetIncome" : 0,
									"userMonthlySavings" : 0,
									"refIncomeTypeId" : $scope.IT[u].refIncomeTypeId ?  $scope.IT[u].refIncomeTypeId : $scope.refId,
									"IncomeType" : $scope.IT,
									"disabled" : false
								};
							$scope.incomes.push(incomeData);
						}
					}else{
						var plus;
						if(plusclk == 'plusClick'){
							plus = 1;
						}else{
							plus = 1;
						}
							for(var u = 0;u<plus;u++){
								
								var incomeData = {
										"userIncomeId" : 0,
										"activeStatus" : "",
										"createdBy" : "",
										"createdOn" : "",
										"updatedBy" : "",
										"updatedOn" : "",
										"userGrossIncome" : 0,
										"frequencyType" : $scope.fTyp,
										"userNetIncome" : 0,
										"userMonthlySavings" : 0,
										"refIncomeTypeId" : $scope.IT[u].refIncomeTypeId ?  $scope.IT[u].refIncomeTypeId : $scope.refId,
										"IncomeType" : $scope.IT,
										"disabled" : false
									};
								$scope.incomes.push(incomeData);
							}
					}
				}else{
					var incomeData = {
							"userIncomeId" : 0,
							"activeStatus" : "",
							"createdBy" : "",
							"createdOn" : "",
							"updatedBy" : "",
							"updatedOn" : "",
							"userGrossIncome" : 0,
							"frequencyType" : $scope.fTyp,
							"userNetIncome" : 0,
							"userMonthlySavings" : 0,
							"refIncomeTypeId" : $scope.IT[0].refIncomeTypeId ?  $scope.IT[0].refIncomeTypeId : $scope.refId,
							"IncomeType" : $scope.IT,
							"disabled" : false
						};
					$scope.incomes.push(incomeData);
				}
				$scope.true_reset = true;
				$scope.success = false;
				};
			
				function loanClone() {
				var loanData = {
				"userLoansId" : 0,
				"activeStatus" : "",
				"createdBy" : "",
				"createdOn" : "",
				"loanTypeId" : 1,
				"manualEntry" : "",
				"updatedBy" : "",
				"updatedOn" : "",
				"userId" : "",
				"userLenderName" : 142,
				"userLoanOutstandingPrincipal" : 1,
				"userLoanPaymentAmount" : 0,
				"userLoanPeriod" : "",
				"userLoanRemainingTenure" : 0
				};
				$scope.loanDatas.push(loanData);
				$scope.true_reset = true;
				$scope.success = false;
				};
			
				function salaryClone() {
				var salaryData = {
				"usersaldednsId" : 0,
				"activeStatus" : "",
				"amount" : 0,
				"createdOn" : "",
				"updatedBy" : "",
				"updatedOn" : "",
				"userId" : "",
				"salaryDednTypeId" : 1
			
				};
				$scope.salaryDatas.push(salaryData);
				$scope.true_reset = true;
				$scope.success = false;
				};
	/* Remove */
	function removeIncome(removeObject,index) {
		if ($scope.userInfo.occupationTypeId == 1 && removeObject.refIncomeTypeId == 1)
		{ }
		else if(($scope.userInfo.occupationTypeId == 2) && (removeObject.refIncomeTypeId == 8 || removeObject.refIncomeTypeId == 9))
		{ }
	/*	else if(($scope.userInfo.occupationTypeId == 3) && (removeObject.refIncomeTypeId == 10 || removeObject.refIncomeTypeId == 11 || removeObject.refIncomeTypeId == 12 || removeObject.refIncomeTypeId == 13))
	*/
		else if ($scope.userInfo.occupationTypeId == 3 && removeObject.refIncomeTypeId == 1)
		{ }
		else if ($scope.userInfo.occupationTypeId == 4 && removeObject.refIncomeTypeId == 2)
		{ }
		else if ($scope.userInfo.occupationTypeId == 5 && removeObject.refIncomeTypeId == 4)
		{ }
		else{
		$scope.incomes = _.without($scope.incomes, removeObject);
		$scope.true_reset = true;
		$scope.success = false;
		}
	}; // remove income
	function removeLoan(removeObject) {
	$scope.loanDatas = _.without(
	$scope.loanDatas, removeObject);
	$scope.true_reset = true;
	$scope.success = false;
	}; // remove loan
	function removeSalary(removeObject) {
	$scope.salaryDatas = _.without(
	$scope.salaryDatas, removeObject);
	$scope.true_reset = true;
	$scope.success = false;
	}
	; // remove salary
	function RemoveRougeChar(convertString) {
	if (convertString.substring(0, 1) == ",") {
	return convertString.substring(1,
	convertString.length);
	}
	return convertString;
	};

	/* function to click the incomebtn */
	$scope.valClone = function() {

	if ($scope.incomes.length < 1) {
		incomeClone();
	} else {

	}
	                	
	}
	function numericOnly(event, val) {
	return regex.numericOnly(event, val);
	}

	function removeText(val) {
	return regex.removeText(val);
	}
	;

	function removeTextTerm(val, i) {
	$scope.loanDatas[i].userLoanRemainingTenure = val
	.replace(/[^0-9\.]+/g, "").replace(
	/[^\w\s]/gi, '');
	}

	function convertNumberToWords(amount) {
	regex.convertNumberToWords(amount);
	}
	/* ERROR */
	function dirtyError(neeval, oldval) {
	if (neeval == true) {
	$scope.true_reset = true;
	$scope.success = false;
	$scope.error = false;
	} else {
	$scope.true_reset = false;
	}
	} ;
	
						function validate(userinfo, incomeinfo, loaninfo, salaryDatas, getsalarytotal,
										sumOFEarnings, sumOFDeductions) {

									$scope.error = false;
									removeAllError();
									
									if (userinfo.occupationTypeId == 0||userinfo.occupationTypeId == "" || userinfo.occupationTypeId == null || userinfo.occupationTypeId == undefined) {
											$('#occupationType').css('border',
													'2px solid red');
											$scope.error = true;
										}else{
											$('#occupationType').removeAttr('style');
										}
									
									if ((userinfo.occupationcategoryid == 0 ||userinfo.occupationcategoryid == "" || userinfo.occupationcategoryid == null || userinfo.occupationcategoryid == undefined)&& (userinfo.occupationTypeId === 1||userinfo.occupationTypeId === 2||userinfo.occupationTypeId === 3||userinfo.occupationTypeId === 4||userinfo.occupationTypeId === 5))  {
											$('#oddCategoy').css('border',
													'2px solid red');
											$scope.error = true;
										}else{
											$('#oddCategoy').removeAttr('style');
										}
										
									if ((userinfo.employerName == "" || userinfo.employerName == null || userinfo.employerName == undefined)&& (userinfo.occupationTypeId === 1||userinfo.occupationTypeId === 2||userinfo.occupationTypeId === 3))  {
											$('#OrganisationName').css('border',
													'2px solid red');
											$scope.error = true;
										}else{
											$('#OrganisationName').removeAttr('style');
										}
									
									if ((userinfo.primaryBank == 0 ||userinfo.primaryBank == "" || userinfo.primaryBank == null || userinfo.primaryBank == undefined)&& (userinfo.occupationTypeId === 1||userinfo.occupationTypeId === 2||userinfo.occupationTypeId === 3||(userinfo.occupationTypeId==4 && userinfo.occupationcategoryid==993) || (userinfo.occupationTypeId==5 && userinfo.occupationcategoryid==995))) {
											$('#primaryBank').css('border',
													'2px solid red');
											$scope.error = true;
										}else{
											$('#primaryBank').removeAttr('style');
										}
										
									if ((userinfo.yearsofServiceinIndustry == "" || userinfo.yearsofServiceinIndustry == null || userinfo.yearsofServiceinIndustry == undefined)&& (userinfo.occupationTypeId === 1||userinfo.occupationTypeId === 2||userinfo.occupationTypeId === 3))  {
											$('#yearsofServiceinIndustry').css('border',
													'2px solid red');
											$scope.error = true;
										}else{
											$('#yearsofServiceinIndustry').removeAttr('style');
										}
									
									if ((userinfo.yearsofServicewithEmployer == "" || userinfo.yearsofServicewithEmployer == null || userinfo.yearsofServicewithEmployer == undefined) && (userinfo.occupationTypeId === 1||userinfo.occupationTypeId === 2||userinfo.occupationTypeId === 3))  {
											$('#yearsofServicewithEmployer').css('border',
													'2px solid red');
											$scope.error = true;
										}else{
											$('#yearsofServicewithEmployer').removeAttr('style');
										}
											
									
									if ((userinfo.businessConstitution == 0||userinfo.businessConstitution == "" || userinfo.businessConstitution == null || userinfo.businessConstitution == undefined) && (userinfo.occupationTypeId === 3||userinfo.occupationTypeId === 2)) {
											$('#businessConstitution').css('border',
													'2px solid red');
											$scope.error = true;
										}else{
											$('#businessConstitution').removeAttr('style');
										}
										
									if ((userinfo.ownershipShare == 0||userinfo.ownershipShare == "" || userinfo.ownershipShare == null || userinfo.ownershipShare == undefined) && (userinfo.occupationTypeId === 3||userinfo.occupationTypeId === 2)) {
											$('#ownershipShare').css('border',
													'2px solid red');
											$scope.error = true;
										}else{
											$('#ownershipShare').removeAttr('style');
										}
									
									if ((userinfo.employeeCount == 0||userinfo.employeeCount == "" || userinfo.employeeCount == null || userinfo.employeeCount == undefined) && (userinfo.occupationTypeId === 3||userinfo.occupationTypeId === 2)) {
											$('#employeeCount').css('border',
													'2px solid red');
											$scope.error = true;
										}else{
											$('#employeeCount').removeAttr('style');
										}
									
									if ((userinfo.officeType == 0 || userinfo.officeType == "" || userinfo.officeType == null || userinfo.officeType == undefined) && (userinfo.occupationTypeId === 3||userinfo.occupationTypeId === 2)) {
											$('#officeType').css('border',
													'2px solid red');
											$scope.error = true;
										}else{
											$('#officeType').removeAttr('style');
										}
									
									if ((userinfo.annualSales == 0 || userinfo.annualSales == "" || userinfo.annualSales == null || userinfo.annualSales == undefined) && (userinfo.occupationTypeId === 3||userinfo.occupationTypeId === 2) && (userinfo.itfiled == 1)) {
											$('#annualSales').css('border',
													'2px solid red');
											$scope.error = true;
										}else{
											$('#annualSales').removeAttr('style');
										}
										
									if ((userinfo.annualProfit == 0 ||userinfo.annualProfit == "" || userinfo.annualProfit == null || userinfo.annualProfit == undefined) && (userinfo.occupationTypeId === 3||userinfo.occupationTypeId === 2) && (userinfo.itfiled == 1)) {
											$('#annualProfit').css('border',
													'2px solid red');
											$scope.error = true;
										}else{
											$('#annualProfit').removeAttr('style');
										}
									
									if ((userinfo.annualSalesPY == 0 || userinfo.annualSalesPY == "" || userinfo.annualSalesPY == null || userinfo.annualSalesPY == undefined) && (userinfo.occupationTypeId === 3||userinfo.occupationTypeId === 2) && (userinfo.itfiled == 1)) {
											$('#annualSalesPY').css('border',
													'2px solid red');
											$scope.error = true;
										}else{
											$('#annualSalesPY').removeAttr('style');
										}
										
									if ((userinfo.annualProfitPY == 0 ||userinfo.annualProfitPY == "" || userinfo.annualProfitPY == null || userinfo.annualProfitPY == undefined) && (userinfo.occupationTypeId === 3||userinfo.occupationTypeId === 2) && (userinfo.itfiled == 1)) {
											$('#annualProfitPY').css('border',
													'2px solid red');
											$scope.error = true;
										}else{
											$('#annualProfitPY').removeAttr('style');
										}
									
										if (userinfo.occupationTypeId === 1||userinfo.occupationTypeId === 2||userinfo.occupationTypeId === 3||(userinfo.occupationTypeId==4 && userinfo.occupationcategoryid==993) || (userinfo.occupationTypeId==5 && userinfo.occupationcategoryid==995)) 
										{ 
										for (var i = 0; i < incomeinfo.length; i++) // empty income
										
										{	
											$('#income' + [ i ] + '').removeClass('redcolorinput');
											// added to check for null undefined and replace with 0
											incomeinfo[i].userNetIncome = incomeinfo[i].userNetIncome == "" || incomeinfo[i].userNetIncome == null || incomeinfo[i].userNetIncome == undefined ? 0 : incomeinfo[i].userNetIncome;
											// ? condition example... 	result = condition ? value1true : value2false;
											
											var am = incomeinfo[i].userNetIncome ? incomeinfo[i].userNetIncome.replace(/\,/g, '') : incomeinfo[i].userNetIncome;
										
											if(am == 0 || incomeinfo[i].userNetIncome.replace(/\,/g, '') == 0 || incomeinfo[i].userNetIncome == "" || incomeinfo[i].userNetIncome == null || incomeinfo[i].userNetIncome == undefined){
												$scope.error = true;
												$scope.success = false;
												$('#income' + [ i ] + '').addClass('redcolorinput');
											} 
										};	
										
										for (var j = 0; j < loaninfo.length; j++) // empty  loan
										{
											$('#PaymentAmount' + [ j ] + '').removeClass('redcolorinput');
											$('#Tenure' + [ j ] + '').removeClass('redcolorinput');
											
												if (loaninfo[j].userLoanRemainingTenure==0) {
												$scope.error = true;
												$scope.success = false;
												$('#Tenure' + [ j ] + '').addClass('redcolorinput');
												}
											if (loaninfo[j].userLoanPaymentAmount==0) {
												$scope.error = true;
												$scope.success = false;
												$('#PaymentAmount' + [j ] + '').addClass('redcolorinput');
												}
										}
										}
									
									if ($scope.error) {
										$scope.error_message = "Kindly fill all the mandatory fields correctly"
										return false;
									} else {
									};
									
									$('#yearsofServiceinIndustry').removeClass('redcolorinput');
									$('#yearsofServicewithEmployer').removeClass('redcolorinput');
									
									if (parseInt(userinfo.yearsofServiceinIndustry) < parseInt(userinfo.yearsofServicewithEmployer)) {
										$('#yearsofServicewithEmployer').addClass('redcolorinput');
										$scope.error = true;
										$scope.error_message = "Current Experience cannot be more than Total Experience";
										$scope.success = false;
										return false;
									} else if ((parseInt($scope.personalInfo[0].age) - 18) < userinfo.yearsofServiceinIndustry) {
										$('#yearsofServiceinIndustry').addClass('redcolorinput');
										$scope.error = true;
										$scope.error_message = "Incorrect Total Experience. Only experience after 18 years of age can be considered.";
										$scope.success = false;
										return false;
									} else {
										
									};
									
									for (var i = 0; i < incomeinfo.length; i++) // empty income
										
										{	
										
											// added to check for null undefined and replace with 0
											incomeinfo[i].userNetIncome = incomeinfo[i].userNetIncome == "" || incomeinfo[i].userNetIncome == null || incomeinfo[i].userNetIncome == undefined ? 0 : incomeinfo[i].userNetIncome;
											// ? condition example... 	result = condition ? value1true : value2false;
											
											var am = incomeinfo[i].userNetIncome ? incomeinfo[i].userNetIncome.replace(/\,/g, '') : incomeinfo[i].userNetIncome;
											
											
											if((incomeinfo[i].refIncomeTypeId == 8 ||incomeinfo[i].refIncomeTypeId == 9 ||incomeinfo[i].refIncomeTypeId == 10 || incomeinfo[i].refIncomeTypeId == 11) && (am < 120000 || incomeinfo[i].userNetIncome.replace(/\,/g, '') < 120000 || incomeinfo[i].userNetIncome == "" || incomeinfo[i].userNetIncome == null || incomeinfo[i].userNetIncome == undefined)){
												$scope.error = true;
												$scope.error_message = "Amount value should be greater than 120000";
												$scope.success = false;
												$('#income' + [ i ] + '').addClass('redcolorinput');
												return false;
											} else
											if (userinfo.occupationTypeId === 1 && am < 10000 && incomeinfo[i].refIncomeTypeId == 1 ||  userinfo.occupationTypeId === 1 && incomeinfo[i].userNetIncome == undefined && incomeinfo[i].refIncomeTypeId == 1 ||  userinfo.occupationTypeId === 1 && incomeinfo[i].userNetIncome < 10000 && incomeinfo[i].refIncomeTypeId == 1 || userinfo.occupationTypeId === 1 && incomeinfo[i].userNetIncome == "" && incomeinfo[i].refIncomeTypeId == 1) {
												$scope.error = true;
												$scope.error_message = "Amount value should be greater than 10000";
												$scope.success = false;
												$('#income' + [ i ] + '').addClass('redcolorinput');
												return false;											
											} 
											// add for business owner income changes
											if (userinfo.occupationTypeId === 3 && am < 10000 && incomeinfo[i].refIncomeTypeId == 1 ||  userinfo.occupationTypeId === 3 && incomeinfo[i].userNetIncome == undefined && incomeinfo[i].refIncomeTypeId == 1 ||  userinfo.occupationTypeId === 3 && incomeinfo[i].userNetIncome < 10000 && incomeinfo[i].refIncomeTypeId == 1 || userinfo.occupationTypeId === 3 && incomeinfo[i].userNetIncome == "" && incomeinfo[i].refIncomeTypeId == 1) {
												$scope.error = true;
												$scope.error_message = "Amount value should be greater than 10000";
												$scope.success = false;
												$('#income' + [ i ] + '').addClass('redcolorinput');
												return false;											
											}
											else{
												
											}
										};	
										
										for (var j = 0; j < loaninfo.length; j++) // empty  loan
										{
											$('#PaymentAmount' + [ j ] + '').removeClass('redcolorinput');
											$('#Tenure' + [ j ] + '').removeClass('redcolorinput');
											
											if (loaninfo[j].userLoanPaymentAmount==0 && loaninfo[j].userLoanRemainingTenure==0) {
												$scope.error = true;
												$scope.error_message = "Please ensure EMI and Months Remaining are entered";
												$scope.success = false;
												$('#PaymentAmount' + [ j ] + '').addClass('redcolorinput');
												$('#Tenure' + [ j ] + '').addClass('redcolorinput');
												return false;
											}
											else if (loaninfo[j].userLoanRemainingTenure==0) {
												$scope.error = true;
												$scope.error_message = "Please ensure Months Remaining is entered";
												$scope.success = false;
												$('#Tenure' + [ j ] + '').addClass('redcolorinput');
												return false;
											}
											else if (loaninfo[j].userLoanPaymentAmount==0) {
												$scope.error = true;
												$scope.error_message = "Please ensure EMI is entered";
												$scope.success = false;
												$('#PaymentAmount' + [j ] + '').addClass('redcolorinput');
												return false;
											}
											else  {
												
											}
										}
										
										for (var i = 0; i < salaryDatas.length; i++) // empty  salary
										{
											if (salaryDatas[i].amount == undefined) {
												$scope.emptysalary.push(i);
											}
										}
									
									if (loaninfo.length != 0) {
										$scope.currentObligation = 1;
									}
						
									if ($scope.error) {
										return false;
									} else {
										return true;
									}
									
									
								};
	
/* Commenting Validate Function
	function validate(userinfo, incomeinfo,
	loaninfo, salaryDatas, getsalarytotal,
	sumOFEarnings, sumOFDeductions) {
	if (userinfo.occupationTypeId == "" || userinfo.occupationTypeId == undefined || userinfo.occupationTypeId == null) {
			$scope.error = true;
			$scope.error_message = "Occupation Type should not be empty";
			$scope.success = false;
			$('#occupationType').addClass('redcolorinput');
			return false;
		}
	for (var i = 0; i < incomeinfo.length; i++) // empty
	// income
	{
		
	if(incomeinfo[i].userNetIncome!=""||incomeinfo[i].userNetIncome!=0||incomeinfo[i].userNetIncome==undefined){
		var am = incomeinfo[i].userNetIncome ? incomeinfo[i].userNetIncome.replace(/\,/g, '') :incomeinfo[i].userNetIncome;
	}
	var am = incomeinfo[i].userNetIncome ? incomeinfo[i].userNetIncome.replace(/\,/g, '') : incomeinfo[i].userNetIncome;
	
	if((incomeinfo[i].refIncomeTypeId == 8 || incomeinfo[i].refIncomeTypeId == 9 || incomeinfo[i].refIncomeTypeId == 10 || incomeinfo[i].refIncomeTypeId == 11) && (am < 120000 || incomeinfo[i].userNetIncome.replace(/\,/g, '') < 120000 || incomeinfo[i].userNetIncome == "" || incomeinfo[i].userNetIncome == null || incomeinfo[i].userNetIncome == undefined)){
		$scope.error = true;
		$scope.error_message = "Amount value should be greater than 120000";
		$scope.success = false;
		$('#income' + [ i ] + '').addClass('redcolorinput');
		return false;
	} else
	if (userinfo.occupationTypeId === 1 && am < 10000 && incomeinfo[i].refIncomeTypeId == 1 ||  userinfo.occupationTypeId === 1 && incomeinfo[i].userNetIncome == undefined && incomeinfo[i].refIncomeTypeId == 1 || userinfo.occupationTypeId === 1 && incomeinfo[i].userNetIncome < 10000 && incomeinfo[i].refIncomeTypeId == 1 || userinfo.occupationTypeId === 1 && incomeinfo[i].userNetIncome == "" && incomeinfo[i].refIncomeTypeId == 1) {

		$scope.error = true;
		$scope.error_message = "Amount value should be greater than 10000";
		$scope.success = false;
		$('#income' + [ i ] + '').addClass('redcolorinput');
		return false;
		} else
	if (userinfo.occupationTypeId === 3 && am < 10000 && incomeinfo[i].refIncomeTypeId == 1 ||  userinfo.occupationTypeId === 3 && incomeinfo[i].userNetIncome == undefined && incomeinfo[i].refIncomeTypeId == 1 || userinfo.occupationTypeId === 3 && incomeinfo[i].userNetIncome < 10000 && incomeinfo[i].refIncomeTypeId == 1 || userinfo.occupationTypeId === 3 && incomeinfo[i].userNetIncome == "" && incomeinfo[i].refIncomeTypeId == 1) {

		$scope.error = true;
		$scope.error_message = "Amount value should be greater than 10000";
		$scope.success = false;
		$('#income' + [ i ] + '').addClass('redcolorinput');
		return false;
	}else if((am < 1000 || incomeinfo[i].userNetIncome < 1000) && 
					(incomeinfo[i].refIncomeTypeId== 2 
					|| incomeinfo[i].refIncomeTypeId==3
					||incomeinfo[i].refIncomeTypeId==4
					|| incomeinfo[i].refIncomeTypeId==5)){
          	    // $scope.error = true;
               // $scope.error_message = "Amount value should be greater than 1000";
               // $scope.success = false;
               // $('#income'+[i]+'').addClass('redcolorinput');
               // return false;
          }
          else{
      }
	};
	for (var i = 0; i < loaninfo.length; i++) // empty
	// loan
	{	
	if (loaninfo[i].userLoanOutstandingPrincipal == undefined
	|| loaninfo[i].userLoanOutstandingPrincipal == null
	|| loaninfo[i].userLoanOutstandingPrincipal == "") {
	$scope.Principal.push(i);
	}
 if (loaninfo[i].userLoanPaymentAmount != 0 && loaninfo[i].userLoanPaymentAmount < 100) {
	$scope.error = true;
	$scope.error_message = "Please Provide Proper EMI";
	$scope.success = false;
	$('#PaymentAmount' + [ i ] + '').addClass('redcolorinput');
	return false;
	}
	}
	for (var i = 0; i < salaryDatas.length; i++) // empty
	// salary
	{
	if (salaryDatas[i].amount == undefined) {
	$scope.emptysalary.push(i);
	}
	}
	if (loaninfo.length != 0) {
	$scope.currentObligation = 1;
	}
	if (getsalarytotal == undefined && userinfo.employerName == null  && userinfo.occupationTypeId === 1) {
	$scope.error = true;
	$scope.error_message = "Please add the Verifiable Income details and Organisation Name";
	$scope.success = false;
	$('#OrganisationName').addClass( 'redcolorinput');
	return false;
	} else if (userinfo.employerName == "" && userinfo.occupationTypeId === 1) {
	$scope.error = true;
	$scope.error_message = "Organisation Name should not be empty";
	$scope.success = false;
	$('#OrganisationName').addClass('redcolorinput');
	return false;
	}else if (userinfo.yearsofServiceinIndustry == null && userinfo.yearsofServicewithEmployer == null) {
	$scope.error = true;
	$scope.error_message = "Total Experience and Current Experience should not be empty";
	$scope.success = false;
	$('#yearsofServicewithEmployer')
	.addClass('redcolorinput');
	$('#yearsofServiceinIndustry')
	.addClass('redcolorinput');
	}
	else if (userinfo.yearsofServiceinIndustry == null || userinfo.yearsofServiceinIndustry == 0  &&  userinfo.occupationTypeId == 1) {
	$scope.error = true;
	$scope.error_message = "Total Experience should be valid years";
	$('#yearsofServiceinIndustry')
	.addClass('redcolorinput');
	$scope.success = false;
	return false;
	} else if (userinfo.yearsofServicewithEmployer == null || userinfo.yearsofServicewithEmployer == 0 && userinfo.occupationTypeId == 1) {
	$scope.error = true;
	$scope.error_message = "Current Experience should not be empty";
	$('#yearsofServicewithEmployer').addClass('redcolorinput');
	$scope.success = false;
	return false;
	} else if (userinfo.userLoanRemainingTenure < 360) {
	$scope.error = true;
	$scope.error_message = "User should not empty";
	$scope.success = false;
	}
	else if (parseInt(userinfo.yearsofServiceinIndustry) < parseInt(userinfo.yearsofServicewithEmployer)) {
	$('#yearsofServicewithEmployer') .addClass('redcolorinput');
	$scope.error = true;
	$scope.error_message = "Current Experience must be less  than Total Experience";
	$scope.success = false;
	return false;
	} else if ((parseInt($scope.personalInfo[0].age) - 21) < userinfo.yearsofServiceinIndustry) {
	$('#yearsofServiceinIndustry').addClass('redcolorinput');
	$scope.error = true;
	$scope.error_message = "Incorrect Experience tenure based on Employment age";
	$scope.success = false;
	}else if ((userinfo.occupationcategoryid == "" || userinfo.occupationcategoryid == null || userinfo.occupationcategoryid == undefined) && (userinfo.occupationTypeId == 2 || userinfo.occupationTypeId == 3 || userinfo.occupationTypeId == 1) ) {
		$('#oddCategoy').addClass('redcolorinput');
		$scope.error = true;
		$scope.error_message = "Please select the Category Type ";
		$scope.success = false;
		return false;
	/*
	}
	else if ((userinfo.categorydetail == "" || userinfo.categorydetail == null || userinfo.categorydetail == undefined) && (userinfo.occupationTypeId == 2 || userinfo.occupationTypeId == 3 || userinfo.occupationTypeId == 1) ) {
		$('#categorydetail').addClass('redcolorinput');
		$scope.error = true;
		$scope.error_message = "Please fill the Category Detail ";
		$scope.success = false;
		return false; */ /*
	} else if (incomeinfo.length < 1) {
	$scope.error = true;
	$scope.error_message = "UserIncome should not empty";
	$scope.success = false;
	}
	else {
	return true;
	}
	};
	ending commented validation */

/* Commenting since not used --- clean up
	$scope.save = function(userinfo, incomeinfo,loaninfo, salaryDatas,e) {
		console.log("userinfo",userinfo);
	for (var i = 0; i < incomeinfo.length; i++) {
	if (incomeinfo[i].refIncomeTypeId == 1 || incomeinfo[i].refIncomeTypeId == 2 || incomeinfo[i].refIncomeTypeId == 4) {
	incomeinfo[i].frequencyType = 1;
	} else {

	}
	}
	removeAllError();
	$scope.emptyincome = [];
	$scope.Tenure = [];
	$scope.Principal = [];
	$scope.PaymentAmount = [];
	$scope.emptysalary = [];
	if (incomeinfo.length != 0) {
	var getsalarytotal = _
	.findWhere(
	incomeinfo,
	{
	refIncomeTypeId : incomeinfo[0].refIncomeTypeId
	});
	}
	var sumOFEarnings = incomeinfo
	.reduce(
	function(s, f) {
	return parseInt(f.userNetIncome.length > 3 ? f.userNetIncome .replace(/,/g, '') : f.userNetIncome) + parseInt(s.length > 3 ? s .replace( /,/g, '') : s); // return
	// the
	// sum
	// of
	// the
	// accumulator
	// and
	// the
	// current
	// time.
	// (as
	// the
	// the
	// new
	// accumulator)
	}, 0); // sum of earning
	var sumOFDeductions = salaryDatas
	.reduce(
	function(s, f) {
	return parseInt(f.amount.length > 3 ? f.amount
	.replace(/,/g,
	'')
	: f.amount)
	+ parseInt(s.length > 3 ? s
	.replace(
	/,/g,
	'')
	: s); // return
	// the
	// sum
	// of
	// the
	// accumulator
	// and
	// the
	// current
	// time.
	// (as
	// the
	// the
	// new
	// accumulator)
	}, 0); // sum of deducation

	if (loaninfo.length == 0) {
	$scope.currentObligation = 0;
	} else {
	$scope.currentObligation = 1;
	}
	if (userinfo.occupationTypeId == 4 || userinfo.occupationTypeId == 17) // save
	// home
	// maker
	{
	$scope.checkFields = {
		"occupationType" : userinfo.occupationTypeId,
		"CreditScore" : userinfo.creditScoreId
	};
	 
	checkHomeMaker(userinfo, incomeinfo,
	loaninfo, salaryDatas,
	getsalarytotal, sumOFEarnings,
	sumOFDeductions,e);
	} else {
	$scope.checkFields = {
		"occupationType" : userinfo.occupationTypeId,
		"yearsofServiceinIndustry" : userinfo.yearsofServiceinIndustry,
		"yearsofServicewithEmployer" : userinfo.yearsofServicewithEmployer,
		"OrganisationName" : userinfo.employerName,
		"CreditScore" : userinfo.creditScoreId
	};
	checkOther(userinfo, incomeinfo,
	loaninfo, salaryDatas,
	getsalarytotal, sumOFEarnings,
	sumOFDeductions,e);
	}
	};
*/

	$scope.next = function(user, income, loan) {
	$scope.success = true;
	dirtyCheck(user, income, loan, 'next');
	}; // next button
	$scope.back = function(user, income, loan) {
	dirtyCheck(user, income, loan, 'back');
	}; // back button
	/* WATCH COLLECTION */
	$scope.$watch('financialformditry.$dirty',
	dirtyError, true);
	$scope.$watch(
	'financialformditry.incomes.$dirty',
	dirtyError, true);
	$scope.$watch(
	'financialformditry.loanDatas.$dirty',
	dirtyError, true);
	$scope
	.$watch(
	'financialformditry.salaryDatas.$dirty',
	dirtyError, true);

/* Commented for clean up
	function checkHomeMaker(userinfo, incomeinfo,
	loaninfo, salaryDatas, getsalarytotal,
	sumOFEarnings, sumOFDeductions,e) {
	$scope.checkFields = {
		"occupationType" : userinfo.occupationTypeId,
		"CreditScore" : userinfo.creditScoreId
	};
	EmptyField();
	if (getsalarytotal == undefined) {
	$scope.error = true;
	$scope.error_message = "Please add your Income Details.";
	$scope.success = false;
	} else if (parseInt(getsalarytotal.userNetIncome.length > 4 ? getsalarytotal.userNetIncome
	.replace(/,/g, '')
	: getsalarytotal.userNetIncome) < sumOFDeductions) {
	$scope.error = true;
	$scope.error_message = "Income should not be lesser than Deduction.";
	$scope.success = false;
	}else {
	save(userinfo, incomeinfo, loaninfo,
	salaryDatas,e);
	}
	};
	
*/

/* Commented for clean up
	function checkOther(userinfo, incomeinfo,loaninfo, salaryDatas, getsalarytotal,sumOFEarnings, sumOFDeductions,e) {
	$scope.removeAllError();
	$scope.emptyincome = [];
	$scope.Tenure = [];
	$scope.Principal = [];
	$scope.PaymentAmount = [];
	$scope.emptysalary = [];
	var checkValidate = validate(userinfo,
	incomeinfo, loaninfo, salaryDatas,
	getsalarytotal, sumOFEarnings,
	sumOFDeductions);
	var checkEmptyField = EmptyField();
	if (checkValidate && !checkEmptyField) {
	save(userinfo, incomeinfo, loaninfo,
	salaryDatas,e);
	}
	};
*/

	function EmptyField() {
	$scope.checkEmptyData = [];
	_
	.every(
	_.keys($scope.checkFields),
	function(currentKey) {
	if ($scope.checkFields[currentKey] == '') {
	$scope.checkEmptyData
	.push(currentKey);
	} else if (currentKey) {

	}
	return $scope.checkEmptyData;
	});
	if ($scope.checkEmptyData.length > 0) {
	_.each($scope.checkEmptyData, function( value) {
	$('#' + value).addClass( 'redcolorinput');
	});
	$scope.error = true;
	$scope.error_message = "Kindly fill the mandatory fields ";
	$scope.success = false;
	}
	;
	return $scope.checkEmptyData.length > 0 ? true : false;
	}
	;

	$scope.next1 = function(userinfo, incomeinfo,loaninfo, salaryDatas, e) {
	if (validate(userinfo, incomeinfo,loaninfo, salaryDatas)) {
	save(userinfo, incomeinfo, loaninfo,salaryDatas, e);
	}

	}
	$scope.save1 = function(userinfo, incomeinfo,loaninfo, salaryDatas, e) {
			if (validate(userinfo, incomeinfo,loaninfo, salaryDatas)) {
				save(userinfo, incomeinfo, loaninfo,salaryDatas, e);
			}

	}

	function save(userinfo, incomeinfo, loaninfo,salaryDatas, e) {
	 var coid = getCookie('coAppId');
	 
	incomedet = [];
	userLoan = [];
	
	for(var k=0;k<incomeinfo.length;k++){
	incomedet.push({
	 "userIncomeId": 0,
	            "activeStatus":"",
	            "createdBy":"",
	            "createdOn":"",
	            "updatedBy":"",
	            "updatedOn":"",
	            "userGrossIncome":0,
	            "userNetIncome": incomeinfo[k].userNetIncome == undefined || incomeinfo[k].userNetIncome == 0 ? 0 : parseInt(incomeinfo[k].userNetIncome.replace(/,/g, '')),
	            "userMonthlySavings":0,
	            "frequencyType": incomeinfo[k].frequencyType,
	            "refIncomeTypeId": incomeinfo[k].refIncomeTypeId
	});
	}
	
	for(var i=0;i<loaninfo.length;i++){
	 userLoan.push({  
	            
	               "userLoansId":loaninfo[i].userLoansId,
	               "activeStatus":"",
	               "createdBy":"",
	               "createdOn":"",
	               "loanTypeId":loaninfo[i].loanTypeId,
	               "manualEntry":"",
	               "updatedBy":"",
	               "updatedOn":"",
	               "userId":"",
	               "userLenderName":loaninfo[i].userLenderName,
	               "userLoanOutstandingPrincipal":loaninfo[i].userLoanOutstandingPrincipal,
	               "userLoanPaymentAmount":loaninfo[i].userLoanPaymentAmount== undefined || loaninfo[i].userLoanPaymentAmount== null || loaninfo[i].userLoanPaymentAmount== 0 ? 0:  loaninfo[i].userLoanPaymentAmount.replace(/,/g, ''),
	               "userLoanPeriod":"",
	               "userLoanRemainingTenure":loaninfo[i].userLoanRemainingTenure
	              
	            });
	         
	}
	$scope.loader = true;
	var companyDetail = localStorage.getItem("coAppOverallCompanyDetails");
	var compDet = JSON.parse(companyDetail);
	var mulUserCoAppFinancialDetails = [{
	"occupationcategoryid":userinfo.occupationcategoryid,
	"categorydetail":userinfo.categorydetail,
	"userEmploymentId":0,
	"activeStatus":0,
	"createdBy" : "",
	"createdOn" : "",
	"primaryMonthlyIncome" : "",
	"secondaryMonthlyIncome" : "",
	"updatedBy" : "",
	"updatedOn" : "",
	"yearsofServiceinIndustry" : userinfo.yearsofServiceinIndustry,
	"yearsofServicewithEmployer" : userinfo.yearsofServicewithEmployer,
	"employerName" : btoa(userinfo.employerName),
	"cin" :  companyDetail == null || companyDetail == undefined ? "" : compDet.data.company.cin,
	"classification" :companyDetail == null || companyDetail == undefined ? "" : compDet.data.company.classification,
	"occupationDesc":"",
	"refCreditScoreId":1,
	 "employerId":0,
	"occupationTypeId": userinfo.occupationTypeId,
	"industrySegmentId":0,
	"industrySegmentName":userinfo.industrySegmentName,
	"startRangeCreditScore":0,
	"endRangeCreditScore":0,
	"assetValue" : userinfo.assetValue == 0 ? 0:parseInt(userinfo.assetValue .replace(/,/g, '')),
	"loanValue" : userinfo.loanValue == 0 ? 0:parseInt(userinfo.loanValue .replace(/,/g, '')),
	"preferedInterestRate" : userinfo.preferedInterestRate,
	"loanTermId" : userinfo.loanTermId,
	"loanTerm":0,
	"userId":parseInt(coid),
	"userIncome" : incomedet,
	"userLoan" : userLoan,
	"userSalaryDeduction" : salaryDatas,
	"employerTypeId" : userinfo.employerTypeId,
	"pensionEligibility":userinfo.pensionEligibility,
	"itfiled":userinfo.itfiled,
	"annualSales" : userinfo.annualSales == null || userinfo.annualSales == undefined ? 0 : userinfo.annualSales,
	"annualProfit" : userinfo.annualProfit == null || userinfo.annualProfit == undefined ? 0 : userinfo.annualProfit,
	"ownershipShare" : userinfo.ownershipShare == null || userinfo.ownershipShare == undefined ? 99 : userinfo.ownershipShare,
	"employeeCount" : userinfo.employeeCount == null || userinfo.employeeCount == undefined ? 0 : userinfo.employeeCount,
	"annualSalesPY" : userinfo.annualSalesPY == null || userinfo.annualSalesPY == undefined ? 0 : userinfo.annualSalesPY,
	"annualProfitPY" : userinfo.annualProfitPY == null || userinfo.annualProfitPY == undefined ? 0 : userinfo.annualProfitPY,
	"officeType" : userinfo.officeType == null || userinfo.officeType == undefined ? 0 : userinfo.officeType,
	"businessConstitution" : userinfo.businessConstitution == null || userinfo.businessConstitution == undefined ? 0 : userinfo.businessConstitution,
	"primaryBank" : userinfo.primaryBank == null || userinfo.primaryBank == undefined ? "" : userinfo.primaryBank,
	
	}];
	
	var postData = $.param({
	"userId": parseInt(getcokkies.getUserId()),
	"mulUserCoAppFinancialDetails" : JSON.stringify(mulUserCoAppFinancialDetails)
	
	});
	var checkterm = false;
	for (var i = 0; i < loaninfo.length; i++) {
	if (parseInt(loaninfo[i].userLoanRemainingTenure) > 360) {
	$('#Tenure' + i).addClass('redcolorinput');
	checkterm = true;
	break;
	} else {
	$('#Tenure' + i).removeClass('redcolorinput');

	}
	}
	if (checkterm) {
	$scope.error = true;
	$scope.error_message = "Terms Should be less than 30 years(360 months) ";
	$scope.success = false;
	$scope.loader = false;

	} else {
	
	getCoApplicantFinancialService.insertMultipleUserCoApplicantFinancials(postData).then(
	function successCallback(response) {
	if (response.data.Result == 'Success') {
	$scope.success = true;
	$scope.success_message = "Your changes have been saved successfully";
	$scope.error = false;
	if(response.data.Page >= 5 ){
	$('#coAppFinNextPage').css('pointer-events', 'all');
						$(".navbar a")[4].style =( 'cursor', 'pointer' );
	                	$(".navbar a")[4].title =""
						$(".navbar a")[4].href = "#/index/credit"
	                	
	                	$(".navbar a")[5].style =( 'cursor', 'pointer' );
	                	$(".navbar a")[5].title =""
	                	$(".navbar a")[5].href = "#/index/Lpi"
	                	
	                	//$(".navbar a")[6].style =( 'cursor', 'pointer' );
	                	//$(".navbar a")[6].title =""
	                	//$(".navbar a")[6].href = "#/index/Lpi"
	                	
	localStorage.setItem('PAGE_COMPLETED',response.data.Page);
	}
	else{
	$('#coAppFinNextPage').css('pointer-events', 'none');
	}
	
	$scope.financialformditry.$setPristine();
	dropDown.updateFinancialDropdown();
	setFinancialValue();
	setFinancialDropdown();
	
	
	if (e.target.className == "btn vcirclebtn pull-right savenxt") {
	$state.go('index.credit');
	//$state.go('index.bankverification');
	
	} 

	} else {
	$scope.success = false;
	$scope.error = true;
	$scope.error_message = response.data.Result;
	$scope.loader = false;
	$scope .removeallerror();
	}
	});
	}
	};

	$scope.nextPages = function(user, income, loan, action) {
	if ($scope.financialformditry.$dirty) {

	var r = confirm("Current page has unsaved data. Are you sure you want to proceed?");

	if (r == true) {
	
	$state.go('index.credit');
	//$state.go('index.bankverification');
	
	} else {
	$scope.error_message = "Highlighted Field values have been modified";
	$('input.ng-dirty').addClass(
	'redcolorinput');
	$('select.ng-dirty').addClass(
	'redcolorinput');
	$scope.sucess = false;

	}
	} else {
	
	$state.go('index.credit');
	//$state.go('index.bankverification');
	

	}
	}
	
	$scope.backPage = function() {
	if ($scope.financialformditry.$dirty) {

	var r = confirm("Current page has unsaved data. Are you sure you want to proceed?");

	if (r == true) {
	$state.go('index.financial');
	} else {
	$scope.error_message = "Highlighted Field values have been modified";
	$('input.ng-dirty').addClass(
	'redcolorinput');
	$('select.ng-dirty').addClass(
	'redcolorinput');
	$scope.sucess = false;

	}
	} else {
	$state.go('index.financial');
	}

	}
	
	
	/* Commented out by Priyaraj on Feb 15, 2023 as https://api.probe42.in/probe_lite/companies API throwing forbidden error */
	/*$scope.orgType = function(val) {
	if($('#OrganisationName').val().length >= 3){
	financialService
	.getcompanylistusingprobe(val)
	.then(
	function successCallback(
	response) {
	var resData=response.data.Page;
	var orgNameArray = JSON.parse(resData);
	$scope.orgNameCUN= orgNameArray.data.companies;
	
	localStorage.setItem('coAppCompaniesData',response.data.Page);
	var ss = JSON.parse(localStorage.getItem('coAppCompaniesData'));
	$scope.companyList=ss.data.companies;
	});
	} else{
	
	}
	}

	$scope.change = function(legalName) {
	financialService.getcompanylistusingcin(
	legalName.cin).then(
	function successCallback(response) {
	localStorage.setItem('coAppOverallCompanyDetails',response.data.Page);
	
	});
	}*/
	/* Commented out by Priyaraj on Feb 15, 2023 as https://api.probe42.in/probe_lite/companies API throwing forbidden error */
	function dirtyCheck(user, income, loan, action) {
	if ($scope.checkassets != true) {
	$scope.omitdata = [];
	var infocheck = JSON.parse(localStorage
	.getItem("UserEmployment"));
	var infoLoan = JSON.parse(localStorage
	.getItem("UserLoan"));
	var infoincome = JSON
	.parse(localStorage
	.getItem("UserIncome"));
	$scope.orignalData = {
	"occupationType" : infocheck.occupationTypeId,
	"yearsofServiceinIndustry" : infocheck.yearsofServiceinIndustry,
	"yearsofServicewithEmployer" : infocheck.yearsofServicewithEmployer,
	"OrganisationName" : infocheck.employerName,
	"CreditScore" : infocheck.refCreditScoreId,
	"assetValue" : infocheck.assetValue
	.toLocaleString('en-IN')
	.replace(
	/\B(?=(\d{3})+(?!\d))/g,
	","),
	"loanValue" : infocheck.loanValue
	.toLocaleString('en-IN')
	.replace(
	/\B(?=(\d{3})+(?!\d))/g,
	","),
	"preferedInterestRate" : infocheck.preferedInterestRate,
	"LoanTerm" : infocheck.loanTermId,
	"itfiled" : infocheck.itfiled,
	
	};
	$scope.changesvalue = {
	"occupationType" : user.occupationTypeId,
	"yearsofServiceinIndustry" : user.yearsofServiceinIndustry,
	"yearsofServicewithEmployer" : user.yearsofServicewithEmployer,
	"OrganisationName" : user.employerName,
	"assetValue" : user.assetValue,
	"loanValue" : user.loanValue,
	"preferedInterestRate" : user.preferedInterestRate,
	"LoanTerm" : user.loanTermId,
	"itfiled" : user.itfiled,
	};
	$scope.checkomitdata = _
	.every(
	_
	.keys($scope.changesvalue),
	function(currentKey) {

	if (!(_
	.has(
	$scope.orignalData,
	currentKey) && _
	.isEqual(
	$scope.changesvalue[currentKey],
	$scope.orignalData[currentKey]))) {
	$scope.omitdata
	.push(currentKey);
	}
	return $scope.omitdata;
	});

	if ($scope.omitdata.length > 0) {
	// alert("greater");
	var r = confirm("Current page has unsaved data. Are you sure you want to proceed?");

	if (r == true) {
	if (action == 'next') {
	if ($rootScope.coApplicant == 1) {
	save(userinfo,
	incomeinfo,
	loaninfo,
	salaryDatas);
	$state
	.go('index.coApplicantfinancial');

	} else {
	save(userinfo,
	incomeinfo,
	loaninfo,
	salaryDatas);
	$state
	.go('index.credit');
	// $state.go('index.bankverification');
	}
	} else {
	save(userinfo, incomeinfo,
	loaninfo,
	salaryDatas);
	$state
	.go('index.financial');
	}
	}

	}

	if ($scope.omitdata.length >= 1) {
	_.each($scope.omitdata, function(
	value) {
	$('#' + value).addClass(
	'redcolorinput');
	})
	$scope.sucess = false;
	$scope.error = false;
	} else if (infoincome.length > income.length) {
	$scope.success = false;
	$scope.error = true;
	$scope.true_reset = true;
	var r = confirm("Current page has unsaved data. Are you sure you want to proceed?");

	if (r == true) {
	if (action == 'next') {

	if ($rootScope.coApplicant == 1) {
	save(userinfo,
	incomeinfo,
	loaninfo,
	salaryDatas);
	$state
	.go('index.coApplicantfinancial');
	} else {
	save(userinfo,
	incomeinfo,
	loaninfo,
	salaryDatas);
	$state
	.go('index.credit');
	// $state.go('index.bankverification');
	}
	} else {
	if ($rootScope.coApplicant == 1) {
	save(userinfo,
	incomeinfo,
	loaninfo,
	salaryDatas);
	$state
	.go('index.coApplicant');
	} else {
	save(userinfo,
	incomeinfo,
	loaninfo,
	salaryDatas);
	$state
	.go('index.customerPersonal');
	}
	}
	}
	$scope.error_message = "Verifiable Income changed";
	} else if (infoLoan.length > loan.length) {
	$scope.success = false;
	$scope.error = true;
	$scope.true_reset = true;
	var r = confirm("Current page has unsaved data. Are you sure you want to proceed?");

	if (r == true) {
	if (action == 'next') {

	if ($rootScope.coApplicant == 1) {
	save(userinfo,
	incomeinfo,
	loaninfo,
	salaryDatas);
	$state
	.go('index.coApplicantfinancial');
	} else {
	save(userinfo,
	incomeinfo,
	loaninfo,
	salaryDatas);
	$state
	.go('index.credit');
	// $state.go('index.bankverification');
	}
	} else {
	if ($rootScope.coApplicant == 1) {
	save(userinfo,
	incomeinfo,
	loaninfo,
	salaryDatas);
	$state
	.go('index.coApplicant');
	} else {
	save(userinfo,
	incomeinfo,
	loaninfo,
	salaryDatas);
	$state
	.go('index.customerPersonal');
	}
	}
	}
	$scope.error_message = "Loan value changed"
	} else if (infoincome.length < income.length) {
	$scope.success = false;
	$scope.error = true;
	$scope.true_reset = true;
	var r = confirm("Current page has unsaved data. Are you sure you want to proceed?");

	if (r == true) {
	if (action == 'next') {

	if ($rootScope.coApplicant == 1) {
	save(userinfo,
	incomeinfo,
	loaninfo,
	salaryDatas);
	$state
	.go('index.coApplicantfinancial');
	// $state.go('index.bankverification');
	} else {
	save(userinfo,
	incomeinfo,
	loaninfo,
	salaryDatas);
	$state
	.go('index.credit');
	}
	} else {
	if ($rootScope.coApplicant == 1) {
	save(userinfo,
	incomeinfo,
	loaninfo,
	salaryDatas);
	$state
	.go('index.coApplicant');
	} else {
	save(userinfo,
	incomeinfo,
	loaninfo,
	salaryDatas);
	$state
	.go('index.customerPersonal');
	}
	}
	}
	$scope.error_message = "Verifiable Income changed";
	} else if (infoLoan.length < loan.length) {
	$scope.success = false;
	$scope.error = true;
	$scope.true_reset = true;
	var r = confirm("Current page has unsaved data. Are you sure you want to proceed?");

	if (r == true) {
	if (action == 'next') {

	if ($rootScope.coApplicant == 1) {
	save(userinfo,
	incomeinfo,
	loaninfo,
	salaryDatas);
	$state
	.go('index.coApplicantfinancial');
	} else {
	save(userinfo,
	incomeinfo,
	loaninfo,
	salaryDatas);
	$state
	.go('index.credit');
	// $state.go('index.bankverification');
	}
	} else {
	if ($rootScope.coApplicant == 1) {
	save(userinfo,
	incomeinfo,
	loaninfo,
	salaryDatas);
	$state
	.go('index.coApplicant');
	} else {
	save(userinfo,
	incomeinfo,
	loaninfo,
	salaryDatas);
	$state
	.go('index.customerPersonal');
	}
	}
	}
	$scope.error_message = "Loan value changed"
	} else {
	if (action == 'next') {
	if ($rootScope.coApplicant == 1) {

	$('#linktxt')
	.html(
	'Co-Applicant Financial<i style="padding: 0 1%;font-size: 16px;" class="fa fa-angle-double-right"></i>');
	$state
	.go('index.coApplicantfinancial');

	} else {
	$('#linktxt')
	.html(
	'Credit Report<i style="padding: 0 1%;font-size: 16px;" class="fa fa-angle-double-right"></i>');
	$state
	.go('index.credit');
	// $state.go('index.bankverification');
	}
	} else {
	if ($rootScope.coApplicant == 1) {
	save(userinfo, incomeinfo,
	loaninfo,
	salaryDatas);
	$state
	.go('index.coApplicant');
	} else {
	save(userinfo, incomeinfo,
	loaninfo,
	salaryDatas);
	$state
	.go('index.customerPersonal');
	}
	}
	}
	} else {
	$scope.omitdata = [];
	var infocheck = JSON.parse(localStorage
	.getItem("UserEmployment"));
	var infoLoan = JSON.parse(localStorage
	.getItem("UserLoan"));
	var infoincome = JSON
	.parse(localStorage
	.getItem("UserIncome"));
	$scope.orignalData = {
	"occupationType" : infocheck.occupationTypeId,
	"yearsofServiceinIndustry" : infocheck.yearsofServiceinIndustry,
	"yearsofServicewithEmployer" : infocheck.yearsofServicewithEmployer,
	"OrganisationName" : infocheck.employerName,
	"CreditScore" : infocheck.refCreditScoreId,
	"itfiled" : infocheck.itfiled,
	
	};
	$scope.changesvalue = {
	"occupationType" : user.occupationTypeId,
	"yearsofServiceinIndustry" : user.yearsofServiceinIndustry,
	"yearsofServicewithEmployer" : user.yearsofServicewithEmployer,
	"OrganisationName" : user.employerName,
	"CreditScore" : user.refCreditScoreId,
	"itfiled" : infocheck.itfiled,
	};
	$scope.checkomitdata = _
	.every(
	_
	.keys($scope.changesvalue),
	function(currentKey) {

	if (!(_
	.has(
	$scope.orignalData,
	currentKey) && _
	.isEqual(
	$scope.changesvalue[currentKey],
	$scope.orignalData[currentKey]))) {
	$scope.omitdata
	.push(currentKey);
	}
	return $scope.omitdata;
	});
	if (infoincome.length > income.length) {
	$scope.success = false;
	$scope.error = true;
	$scope.true_reset = true;
	var r = confirm("Current page has unsaved data. Are you sure you want to proceed?");

	if (r == true) {
	if (action == 'next') {

	if ($rootScope.coApplicant == 1) {
	$state
	.go('index.coApplicantfinancial');
	} else {
	$state
	.go('index.credit');
	// $state.go('index.bankverification');
	}
	} else {

	if ($rootScope.coApplicant == 1) {
	$state
	.go('index.coApplicant');
	} else {
	$state
	.go('index.customerPersonal');
	}
	}
	}
	$scope.error_message = "Verifiable Income changed";
	} else if (infoLoan.length > loan.length) {
	$scope.success = false;
	$scope.error = true;
	$scope.true_reset = true;
	var r = confirm("Current page has unsaved data. Are you sure you want to proceed?");

	if (r == true) {
	if (action == 'next') {

	if ($rootScope.coApplicant == 1) {
	$state
	.go('index.coApplicantfinancial');
	} else {
	$state
	.go('index.credit');
	// $state.go('index.bankverification');
	}
	} else {
	if ($rootScope.coApplicant == 1) {
	$state
	.go('index.coApplicant');
	} else {
	$state
	.go('index.customerPersonal');
	}
	}
	}
	$scope.error_message = "Loan value changed"
	} else if (infoincome.length < income.length) {
	$scope.success = false;
	$scope.error = true;
	$scope.true_reset = true;
	var r = confirm("Current page has unsaved data. Are you sure you want to proceed?");

	if (r == true) {
	if (action == 'next') {

	if ($rootScope.coApplicant == 1) {
	$state
	.go('index.coApplicantfinancial');
	} else {
	$state
	.go('index.bankverification');
	}
	} else {
	if ($rootScope.coApplicant == 1) {
	$state
	.go('index.coApplicant');

	} else {
	$state
	.go('index.customerPersonal');
	}
	}
	}
	$scope.error_message = "Verifiable Income changed";
	} else if (infoLoan.length < loan.length) {
	$scope.success = false;
	$scope.error = true;
	$scope.true_reset = true;
	var r = confirm("Current page has unsaved data. Are you sure you want to proceed?");

	if (r == true) {
	if (action == 'next') {

	if ($rootScope.coApplicant == 1) {
	$state
	.go('index.coApplicantfinancial');
	} else {
	$state
	.go('index.credit');
	//$state.go('index.bankverification');
	}
	} else {
	if ($rootScope.coApplicant == 1) {
	$state
	.go('index.coApplicant');
	} else {
	$state
	.go('index.customerPersonal');
	}
	}
	}
	$scope.error_message = "Loan value changed"
	} else {
	if (action == 'next') {
	if ($rootScope.coApplicant == 1) {
	$state
	.go('index.coApplicantfinancial');
	} else {
	$state
	.go('index.bankverification');
	}
	} else {

	if ($rootScope.coApplicant == 1) {
	$state
	.go('index.coApplicant');
	} else {
	$state.go('index.customerPersonal');
	}
	}
	}
	}
	}
	
	$scope.selectedCattgoryType = function(selectCatId,event){
		getCatLabel(selectCatId,event);
		if (selectCatId == 994 || selectCatId == 996){
		$scope.userInfo.primaryBank = "";
		$scope.userInfo.itfiled= "0"; 
		}
	}

	/**
	 * Setting Label according to category type
	 */
//	$scope.selectedCattgoryType = function(selectCatId,event){
	function getCatLabel(selectCatId,event){
		
		if($scope.occCatyId == undefined){
			$('#fdyLabel').text("Enter the Details");
		}else{
		for (var idd in $scope.occCatyId){
//			$('#fdyLabel').text("Enter the Details");
				if(selectCatId == $scope.occCatyId[idd]['categoryid']){
					$('#fdyLabel').text($scope.occCatyId[idd]['categorytext']);
				}
			}
		}
		
		if(event == 'ctgyChange' && selectCatId != null ){
			$scope.incomes = [];
			incomeClone(event,selectCatId)
			$scope.loanDatas = [];
		}

	}
	
	
		/**
		 * ng-init to call service if the occupation type is 2 or 3
		 */
		$scope.occTyp =function(occType){
			if(occType == 3 || occType == 2 || occType == 1){
			console.log(occType);
			var occId = $.param({
				"OccupationtypeId":occType
			});
			
			financialService.getListOfOccupationCategory(occId).then(function successCallback(response){
				$scope.occCatyId = JSON.parse(response.data.Result);
			});
			}
		}
	  }

	]);
})();