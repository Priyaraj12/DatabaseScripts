var Ps = Ps || {};

(function() {
	'use strict';
	angular
			.module('assets')
			.controller(
					'assetsController',
					[
							'$state',
							'$http',
							'$scope',
							'getcokkies',
							'config',
							'$rootScope',
							'dropDown',
							'getassetservice',
							function($state, $http, $scope, getcokkies, config, $rootScope, dropDown, getassetservice) {
								dropDown.updateFinancialDropdown();
								$scope.isMobile =getBrowserInfo();
								var assetsNav = parseInt(localStorage.getItem('PAGE_COMPLETED'));
								var seletedCity = "";
								$rootScope.PAGE_COMPLETED = assetsNav;
								$scope.PAGE_COMPLETED = assetsNav;
								$scope.loantimeframe = [{
									"id":0,
									"timeframe_desc":""
								}];
								if(assetsNav >= 1){
									$('.personalLabel').css('pointer-events', 'auto');
								}
								else{
									$('.personalLabel').css('pointer-events', 'none');
								}
								angular.element(document).ready(function() {
									$('#selector6').trigger('click');
									
								});
								if (localStorage.getItem("userLpiStatus") == "1") {
									$("input").prop("disabled", false);
								}else{
									$("input").prop("disabled", true);
									$("select").prop("disabled", true);
									$("checkbox").prop("disabled", true);
									$("radio").prop("disabled", true);
									$("button").prop("disabled", true);
									$scope.savnxt = true;
								}
								
								if ($rootScope.username == "Guest" && localStorage.getItem('count') == null && $scope.isMobile == false) {
									$('#myyModal').modal('show');

								}else if(($rootScope.PAGE_COMPLETED < 1 ) && localStorage.getItem('count') == null && $scope.isMobile == true)
								{
									$('#mobileModalCombined').modal('show');
								}else{
									if($scope.isMobile == true) {
										$('#mobileModal').modal('show');
									}
								}
								$scope.omitdata = [];
								$scope.newbuild = false;
								$rootScope.username = localStorage
										.getItem("user_name");
								$rootScope.assetShow = parseInt(localStorage
										.getItem("assetShow"));
								$rootScope.coApplicant = parseInt(localStorage
										.getItem("coApplicant"));
								$scope.dropdown = JSON.parse(localStorage
										.getItem('dropdown'));
								
								$scope.LoanLenderType = JSON
										.parse($scope.dropdown.LoanLenderType);
							
								
								$scope.checkemptydata = [];
								$scope.result = false;
								$scope.addbuilder = {};
								$scope.Add = {};
								$scope.RERA_Compliance = [ {
									'name' : "Yes"
								}, {
									'name' : "No"
								}, {
									'name' : "Dont know"
								} ]
								$scope.ApprovedPLot = [ {
									'name' : "Yes"
								}, {
									'name' : "No"
								} ]

								$scope.CurrentLoanStatus = [ {
									'name' : "Yes"
								}, {
									'name' : "No"
								} ]
								
								function getZipInfo(zip, f) {
									var data = $.param({
										'sessionId' : parseInt(getcokkies
												.getsessionId()),
										'zipCode' : zip
									});
									getassetservice
											.getZipInfo(data)
											.then(
													function successCallback(
															response) {
														localStorage.setItem("mint", 600000);
														$rootScope.SessionTime = 600000;
														$scope.citys = JSON
																.parse(response.data.Result);
														$scope.loader = false;
													},
													function errorCallback(response) {
													});
								};
								$scope.loader = true;
								$scope.newbuilder = function() {
									$scope.newbuild = true;
								}
								$scope.projectDiv = false;
								$scope.showDiv = false;
								$scope.projectAdded = function() {
									$scope.projectDiv = !$scope.projectDiv;
									$scope.showDiv = !$scope.showDiv;
								}
								$scope.closeAddProject = function() {
									$scope.projectDiv = !$scope.projectDiv;
									$scope.showDiv = !$scope.showDiv;
								}
								$scope.onFocus = function(e) {
									$timeout(function() {
										$(e.target).trigger('input');
										$(e.target).trigger('change'); // for
										// IE
									});
								};
								$("#LoanTerm").removeClass('input ng-valid');

								getassetservice.getRefloantimeframe()
								.then (
									function successCallback(response) {
										$scope.loanTimeFrame = JSON.parse(response.data.Result);
										
										$scope.selectedRefloanTime = JSON.parse(localStorage.getItem("assetFinancing")).loantimeframe;
										
										if($scope.selectedRefloanTime !=undefined){
											$scope.loantimeframe = $scope.loanTimeFrame[0];
											for (var i = 0; i < $scope.loanTimeFrame.length; i++){
												if ($scope.loanTimeFrame[i].id == $scope.selectedRefloanTime){
													$scope.loantimeframe = $scope.loanTimeFrame[i]
												}
											}
										}								
									},
									function errorCallback(response) {
										// called asynchronously if
										// an error occurs
										// or server returns
										// response with an error
										// status.
									});
								getassetservice.getAssetProductType().then(
												function successCallback(response) {
													//adding the changes by Priyaraj on 6th February 2023, not to show propertytypeid 6 and 7
													$scope.productTypes = (JSON.parse(response.data.Result)); 
													$scope.filteredProductsForLoanTypesOneAndTwo = [];
													$scope.filteredProductsForLoanTypeThree = [];
													for (var i = 0; i < $scope.productTypes.length; i++) {
															var productTypeId = $scope.productTypes[i].productTypeId;
															if (productTypeId != 6 && productTypeId != 7) {
																$scope.filteredProductsForLoanTypesOneAndTwo.push($scope.productTypes[i]);
															}
															if (productTypeId == 6 || productTypeId == 7) {
																$scope.filteredProductsForLoanTypeThree.push($scope.productTypes[i]);
															}
													}
													
													
													if ($scope.Assets.loantypeid == 1 || $scope.Assets.loantypeid == 2) {									
														$scope.assetsProducts = $scope.filteredProductsForLoanTypesOneAndTwo;
													}
													if ($scope.Assets.loantypeid == 3) {
														$scope.assetsProducts = $scope.filteredProductsForLoanTypeThree;
													}
													$scope.removeLastPurchaseVal();
												},
												function errorCallback(response) {
													// called asynchronously if
													// an error occurs
													// or server returns
													// response with an error
													// status.
												});
															
								$scope.shouldShow = function (assetsProducts) {
									  // put your authorization logic here
									 
									if($scope.Assets.propertyIdentifierId == '1'){
										 
										  return  assetsProducts.refnum != 99;

									}else{
										 
										 $('#purchaseVal').val('number:99');
										  return  assetsProducts.refnum != 1 && assetsProducts.refnum != 2;

									}
									}
								$scope.removeLastPurchaseVal=function(){	
							}
								getassetservice
										.getAssetType()
										.then(
												function successCallback(
														response) {
													$scope.assetstypes = (JSON
															.parse(response.data.Result));

												},
												function errorCallback(response) {
													// called asynchronously if
													// an error occurs
													// or server returns
													// response with an error
													// status.
												});
								getassetservice
										.loanTerm()
										.then(
												function successCallback(
														response) {
													$scope.eligibleLoanTerm = response.data.loanTerm;
												},
												function errorCallback(response) {
													// called asynchronously if
													// an error occurs
													// or server returns
													// response with an error
													// status.
												});
								getassetservice
										.getListOfBuilderProjectAndZipCode()
										.then(
												function successCallback(response) {
													$scope.builderprojects = JSON
															.parse(response.data.Result);
													
													
												},
												function errorCallback(response) {
													// called asynchronously if
													// an error occurs
													// or server returns
													// response with an error
													// status.
													
												});
								$scope.assetinfo = JSON.parse(localStorage.getItem("asset"));
								$scope.Assets = JSON.parse(localStorage.getItem("assetFinancing"));
						    	
						     	
								$scope.Assets.loantime = parseInt($scope.Assets.loantimeframe);
								$scope.Assets.refnum = $scope.Assets.productTypeId;
								$scope.Assets.location = $scope.Assets.city;
								$scope.Assets.projectZip = $scope.assetinfo.projectZip;
								$scope.Assets.projectName = $scope.assetinfo.projectName;
								var projId = $scope.assetinfo.projectId;
								$('#addProjectName').attr('value',projId);
								$scope.Assets.preferedInterestRate = $scope.Assets.preferredInterestRate;
								$scope.Assets.builderName = $scope.assetinfo.builderName;
								var bulderId = $scope.assetinfo.builderId;
								$('#addBuilderName').attr('value',bulderId);
								
								$scope.Assets.reReCompliance = $scope.assetinfo.reracompliance;
								$scope.Assets.assetArea = $scope.assetinfo.assetArea;
								$scope.Assets.assetFloor = $scope.assetinfo.assetFloor;
								$scope.Assets.location = $scope.Assets.city;
								$scope.Assets.assetValue = $scope.Assets.assetValue ? $scope.Assets.assetValue : 0;
								$scope.Assets.loanValue = $scope.Assets.loanValue ? $scope.Assets.loanValue : 0;
								$scope.Assets.loanTermId = $scope.Assets.loanTermId ? $scope.Assets.loanTermId : 0;
								$scope.Assets.currentLoanTenure = $scope.Assets.currentLoanTenure ? $scope.Assets.currentLoanTenure : 0;
								$scope.Assets.currentLoanBalanceTenure = $scope.Assets.currentLoanBalanceTenure ? $scope.Assets.currentLoanBalanceTenure : 0;
								$scope.Assets.currentLoanOutstandingAmount = $scope.Assets.currentLoanOutstandingAmount ? $scope.Assets.currentLoanOutstandingAmount : 0;
								$scope.Assets.currentEMI = $scope.Assets.currentEmi ? $scope.Assets.currentEmi : 0;
									
								$scope.Assets.assetValue = $scope.Assets.assetValue == 0 ? '': $scope.Assets.assetValue.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
								$scope.Assets.loanValue = $scope.Assets.loanValue == 0 ? '': $scope.Assets.loanValue.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
								$scope.Assets.loanTermId = $scope.Assets.loanTermId == 0 ? '': $scope.Assets.loanTermId;
								$scope.Assets.currentLoanTenure = $scope.Assets.currentLoanTenure == 0 ? '' : $scope.Assets.currentLoanTenure.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
								$scope.Assets.currentLoanBalanceTenure = $scope.Assets.currentLoanBalanceTenure == 0 ? '': $scope.Assets.
								currentLoanBalanceTenure.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
								$scope.Assets.currentLoanOutstandingAmount = $scope.Assets.currentLoanOutstandingAmount == 0 ? '': $scope.Assets.currentLoanOutstandingAmount.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
								$scope.Assets.currentEMI = $scope.Assets.currentEMI == 0 ? '': $scope.Assets.currentEMI.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
								$scope.Assets.CrLenderName = $scope.Assets.currentLender;
								$scope.Assets.zipId = $scope.assetinfo.city;
								$scope.Assets.approvedPlot = $scope.assetinfo.assetApprovedStatus == 1 ? "Yes": "No";
								$scope.Assets.currentLoanStatus = $scope.Assets.currentLoanStatus == 1 ? "Yes": "No";
								$scope.Assets.lenderName = $scope.assetinfo.originalLenderId;
								$scope.Assets.propertyIdentifierId = $scope.Assets.propertyIdentifierId;
								
								$scope.Assets.loantypeid = $scope.Assets.loanTypeId ? $scope.Assets.loanTypeId : 1;
								//alert($scope.Assets.loantypeid);
          						$scope.loader = false;
								getZipInfo($scope.Assets.projectZip, "first");
								$scope.save = function(asset,e) {
								$scope.builderId=parseInt($("#addBuilderName1 option[value='" + $.trim($('#addBuilderName').val()) + "']").attr('id'));
								$scope.projectId=parseInt($("#projectname option[value='" + $.trim($('#addProjectName').val()) + "']").attr('id'));
											
							if(asset.assetValue && asset.loanValue){
									var assetval = asset.assetValue.replace(/\,/g, '');
									var loanval = asset.loanValue.replace(/\,/g, '');
							}else{
							}
									asset.city = $("#location").val();
									asset.builderName = $.trim(angular.element('#addBuilderName').val());
									asset.projectName = $.trim(angular.element('#addProjectName').val());
									asset.propertyIdentifierId = angular.element('input[id="PropertyIdentifier"]:checked').val();
									asset.loantypeid = angular.element('input[id="LoanObjective"]:checked').val();
									var loantime = isNaN(asset.loantime) ? asset.loantime = "" : asset.loantime;
									asset.loantime = loantime;
									
									asset.loanOutstandingamount = $('#CrLoanValue').val();
									asset.loanBalancearansfer =  $('#currentLoanTerm').val();
									asset.currentLender =$('#loanlender').val();
									asset.currentEmi = $('#CrEMI').val();
									var product = _.findWhere($scope.assetsProducts, {refnum : asset.refnum});
									$scope.errorremove();
											
									
									 if (asset.propertyIdentifierId ) {
										$scope.decimalcheck = /^\d+(?:\.\d+)?$/;
										if (asset.refnum == 1 ) {
											if(asset.loantypeid==2){
												
												$scope.checkFields = {
														"addBuilderName":asset.builderName,
														"addProjectName":asset.projectName,
														"AssetValue":asset.assetValue,
														"LoanTerm" : asset.loanTermId,
														"LoanValue" : asset.loanValue,
														"propertyIdentifierId" : asset.propertyIdentifierId,
														"loantime":asset.loantime,
														"location":asset.city,
														"CrLoanValue":asset.loanOutstandingamount,
														"currentLoanTerm":asset.loanBalancearansfer,
														"CrEMI":asset.currentEmi,
														"loanlender":asset.currentLender,
														
													};
												
											}else {
												
											$scope.checkFields = {
												"addBuilderName":asset.builderName,
												"addProjectName":asset.projectName,
												"AssetValue":asset.assetValue,
												"LoanTerm" : asset.loanTermId,
												"LoanValue" : asset.loanValue,
												"propertyIdentifierId" : asset.propertyIdentifierId,
												"loantime":asset.loantime,
												"location":asset.city,
																							
											};
											}
											
											 
										} else if (asset.refnum == 1 && asset.propertyIdentifierId == 1 ){
											if(asset.loantypeid==2){
											$scope.checkFields = {
													"addBuilderName":asset.builderName,
													"addProjectName":asset.projectName,
													"AssetValue":asset.assetValue,
													"LoanTerm" : asset.loanTermId,
													"LoanValue" : asset.loanValue,
													"propertyIdentifierId" : asset.propertyIdentifierId,
													"loantime":asset.loantime,
													"location":asset.city,
													"purchaseVal" : asset.refnum, 
													"CrLoanValue":asset.loanOutstandingamount,
													"currentLoanTerm":asset.loanBalancearansfer,
													"CrEMI":asset.currentEmi,
													"loanlender":asset.currentLender,
												};
											}else{
												$scope.checkFields = {
														"addBuilderName":asset.builderName,
														"addProjectName":asset.projectName,
														"AssetValue":asset.assetValue,
														"LoanTerm" : asset.loanTermId,
														"LoanValue" : asset.loanValue,
														"propertyIdentifierId" : asset.propertyIdentifierId,
														"loantime":asset.loantime,
														"location":asset.city,
														"purchaseVal" : asset.refnum, 
													
													};
											}
											
											
											
										}else if ((asset.refnum == 2 || asset.refnum == 3 || asset.refnum == 4 || asset.refnum == 5) && asset.propertyIdentifierId == 1 ){
											
											if(asset.loantypeid==2){
											$scope.checkFields = {
													"AssetValue":asset.assetValue,
													"LoanTerm" : asset.loanTermId,
													"LoanValue" : asset.loanValue,
													"propertyIdentifierId" : asset.propertyIdentifierId,
													"loantime":asset.loantime,
													"location":asset.city,
													"purchaseVal" : asset.refnum, 
													"CrLoanValue":asset.loanOutstandingamount,
													"currentLoanTerm":asset.loanBalancearansfer,
													"CrEMI":asset.currentEmi,
													"loanlender":asset.currentLender,
													
												};
											}else{
												$scope.checkFields = {
														"AssetValue":asset.assetValue,
														"LoanTerm" : asset.loanTermId,
														"LoanValue" : asset.loanValue,
														"propertyIdentifierId" : asset.propertyIdentifierId,
														"loantime":asset.loantime,
														"location":asset.city,
														"purchaseVal" : asset.refnum, 
														
														
													};
											}
											
										}else {
											if(asset.loantypeid==2){ 
											$scope.checkFields = {
											 "AssetValue" : asset.assetValue,
											 "LoanTerm": asset.loanTermId,
											 "LoanValue": asset.loanValue,
											 "propertyIdentifierId":asset.propertyIdentifierId,
											 "loantime":asset.loantime,
											 "CrLoanValue":asset.loanOutstandingamount,
											 "currentLoanTerm":asset.loanBalancearansfer,
											 "CrEMI":asset.currentEmi,
											 "loanlender":asset.currentLender,
											 "purchaseVal" : asset.refnum,
											 "location":asset.city,
											};
											
											}else{
												$scope.checkFields = {
														 "AssetValue" : asset.assetValue,
														 "LoanTerm": asset.loanTermId,
														 "LoanValue": asset.loanValue,
														 "propertyIdentifierId":asset.propertyIdentifierId,
														 "loantime":asset.loantime,
														 "purchaseVal" : asset.refnum == 0 ? asset.refnum = "0" : asset.refnum,
														 "location":asset.city,
														 
														};
											}
											

										}
										
										$scope.checkemptydata = [];
										_.every(_.keys($scope.checkFields),
														function(currentKey) {
															if ($scope.checkFields[currentKey] == '' || $scope.checkFields[currentKey] == undefined
																	|| $scope.checkFields[currentKey] == null   ) {
																$scope.checkemptydata
																		.push(currentKey);
															}
															return $scope.checkemptydata;
														});
										
										var loanout = asset.loanOutstandingamount.replace(/\,/g, '');
										var balten = asset.loanBalancearansfer.replace(/\,/g, '');
										var crEMI = asset.currentEmi.replace(/\,/g, '');
										var minCrEMI = loanout / balten;
										
																	$('#CrEMI').removeClass('redcolorinput');
																	$('#currentLoanTerm').removeClass('redcolorinput');
																	$('#CrLoanValue').removeClass('redcolorinput');
																	$('#LoanTerm').removeClass('redcolorinput');
																	$('#LoanValue').removeClass('redcolorinput');
																	$('#AssetValue').removeClass('redcolorinput');										
										
										if ($scope.checkemptydata.length > 0) {
											_.each($scope.checkemptydata,function(value) {
															$('#' + value).addClass('redcolorinput');
											});
											
											
											$scope.error = true;
											$scope.error_message = "Please fill all mandatory fields.";
											$scope.sucess = false;
										} else if (asset.assetFloor > 65) {
											$scope.error = true;
											$scope.sucess = false;
											$scope.error_message = "Asset floor limit for 65 floors only.";
										} else if (asset.assetValue == 0) {
											$scope.error = true;
											$scope.error_message = "Property value cannot be zero";
											$scope.success = false;
											$('#AssetValue').addClass('redcolorinput');
										} else if (assetval < 600000) {
											$scope.error = true;
											$scope.error_message = "Minimum Property value should be 6,00,000";
											$scope.success = false;
											$('#AssetValue').addClass('redcolorinput');
										} else if (assetval > 50000000) {
											$scope.error = true;
											$scope.error_message = "Property value should not exceed 5,00,00,000";
											$scope.success = false;
											$('#AssetValue').addClass('redcolorinput');
										} else if (asset.loanValue == 0) {
											$scope.error = true;
											$scope.error_message = "Loan value cannot be zero";
											$scope.success = false;
											$('#LoanValue').addClass('redcolorinput');
										} else if (loanval < 500000) {
											$scope.error = true;
											$scope.error_message = "Minimum Loan value should be 5,00,000";
											$scope.success = false;
											$('#LoanValue').addClass('redcolorinput');
										} else if (loanval > 50000000) {
											$scope.error = true;
											$scope.error_message = "Loan value should not exceed 5,00,00,000";
											$scope.success = false;
											$('#LoanValue').addClass('redcolorinput');
										} else if (parseInt(asset.assetValue.replace(/,/g, '')) < parseInt(asset.loanValue.replace(/,/g, ''))) {
											$scope.error = true;
											$scope.error_message = "Required Loan value must be less than Property Value";
											$scope.sucess = false;
											$('#LoanValue').addClass('redcolorinput');
										} else if (asset.loanTermId == 0) {
											$scope.error = true;
											$scope.error_message = "Loan Term cannot be zero";
											$scope.success = false;
											$('#LoanTerm').addClass('redcolorinput');
										} else if (asset.loanTermId < 36) {
											$scope.error = true;
											$scope.error_message = "Minimum Loan Term should be 36 Months (3 years)";
											$scope.success = false;
											$('#LoanTerm').addClass('redcolorinput');
										} else if (asset.loanTermId > 360) {
											$scope.error = true;
											$scope.error_message = "Loan Term should not exceed 360 Months";
											$scope.success = false;
											$('#LoanTerm').addClass('redcolorinput');
										} else if ((asset.loanOutstandingamount == 0) && (asset.loantypeid==2)) {
											$scope.error = true;
											$scope.error_message = "Outstanding Loan amount cannot be 0 for BT";
											$scope.success = false;
											$('#CrLoanValue').addClass('redcolorinput');
										} else if ((parseInt(asset.loanOutstandingamount.replace(/,/g, '')) < 500000) && (asset.loantypeid==2)) {
											$scope.error = true;
											$scope.error_message = "Minimum Outstanding Loan amount for BT should be 5,00,000";
											$scope.success = false;
											$('#CrLoanValue').addClass('redcolorinput');
										} else if ((parseInt(asset.loanOutstandingamount.replace(/,/g, '')) > 50000000) && (asset.loantypeid==2)) {
											$scope.error = true;
											$scope.error_message = "Maximum Outstanding Loan amount for BT should be 5,00,00,000";
											$scope.success = false;	
											$('#CrLoanValue').addClass('redcolorinput');
										} else if ((parseInt(asset.loanOutstandingamount.replace(/,/g, '')) >= assetval) && (asset.loantypeid==2)) {
											$scope.error = true;
											$scope.error_message = "Outstanding Loan Amount should be less than Property Value";
											$scope.success = false;
											$('#CrLoanValue').addClass('redcolorinput');
										//Adding additional condition by Priyaraj on 31-03-2023
										} else if ((parseInt(asset.loanOutstandingamount.replace(/,/g, '')) > loanval) && (asset.loantypeid==2)) {
											$scope.error = true;
											$scope.error_message = "Required Loan Amount should be greater than or equal to outstanding loan amount";
											$scope.success = false;
											$('#LoanValue').addClass('redcolorinput');
										//Adding additional condition by Priyaraj on 31-03-2023										
										} else if ((asset.loanBalancearansfer == 0) && (asset.loantypeid==2)) {
											$scope.error = true;
											$scope.error_message = "Balance Tenure cannot be 0 for BT";
											$scope.success = false;	
											$('#currentLoanTerm').addClass('redcolorinput');
										} else if ((asset.loanBalancearansfer < 12) && (asset.loantypeid==2)) {
											$scope.error = true;
											$scope.error_message = "Minimum Balance Tenure for BT should be 12 months";
											$scope.success = false;	
											$('#currentLoanTerm').addClass('redcolorinput');
										} else if ((asset.loanBalancearansfer > 360) && (asset.loantypeid==2)) {
											$scope.error = true;
											$scope.error_message = "Maximum Balance Tenure for BT should be 360 months";
											$scope.success = false;	
											$('#currentLoanTerm').addClass('redcolorinput');
										} else if ((asset.currentEmi == 0) && (asset.loantypeid==2)) {
											$scope.error = true;
											$scope.error_message = "Current EMI cannot be 0 for BT";
											$scope.success = false;	
											$('#CrEMI').addClass('redcolorinput');
										} else if ((parseInt(asset.currentEmi.replace(/,/g, '')) >= parseInt(asset.loanOutstandingamount.replace(/,/g, ''))) && (asset.loantypeid==2)) {
											$scope.error = true;
											$scope.error_message = "Current EMI should be less than Outstanding Loan Amount";
											$scope.success = false;	
											$('#CrEMI').addClass('redcolorinput');
										} else if ((asset.loantypeid==2) && (crEMI <= minCrEMI)) {
											$scope.error = true;
											$scope.error_message = "Current EMI is incorrect for given Outstanding Loan Amount and Balance Tenure";
											$scope.success = false;	
											$('#CrEMI').addClass('redcolorinput');
										}
									
										else {
											if (!angular.isNumber(asset.zipId)) {
												if (asset.zipId != null) {
													$scope.Assets.city = asset.zipId;
													var obj = _.findWhere($scope.citys,
															{
																		city : asset.zipId
																	});
													$scope.Assets.zipId = obj.zipId;
												}
											}
											$scope.loader = true;
											var builder = btoa(asset.builderName);
											var projjj = btoa(asset.projectName);
											var loanframe = asset.loantime;
											var assetData = {
												"builderId" : $scope.builderId ? $scope.builderId: '',
												"builderProjectId" : $scope.projectId ? $scope.projectId: '',
												"projectZip" : asset.projectZip,
												"builderName" : builder,
												"projectName" :projjj,
												"assetValue" : angular.isNumber(asset.assetValue) ? asset.assetValue: parseInt(asset.assetValue.replace(/,/g,'')),
												"assetArea" : angular.isNumber(asset.assetArea) ? asset.assetArea: parseInt(asset.assetArea.replace(/,/g,'')),
												"assetFloor" : parseInt(asset.assetFloor),
												"loanTermId" : parseInt(asset.loanTermId),
												"loanValue" : angular.isNumber(asset.loanValue) ? asset.loanValue: parseInt(asset.loanValue.replace(/,/g,'')),
												"preferredInterestRate" : 0,
												"reRaCompliance" : asset.reReCompliance,
												"zipId" : asset.zipId,
												"city" : asset.city == undefined || asset.city.includes("string") ? '' : asset.city,
												"location" : asset.city == undefined || asset.city.includes("string") ? '' : asset.city,
												"productTypeId" : product == undefined ? '99' : product.productTypeId,
												"assetType" : asset.assetType,
												"originalLenderId" : asset.lenderName,
												"assetApprovedStatus" : (asset.approvedPlot == "Yes") ? 1: 0,
												"currentLoanBalanceTenure" : angular.isNumber(asset.currentLoanBalanceTenure) ? asset.currentLoanBalanceTenure: parseInt(asset.currentLoanBalanceTenure.replace(/,/g,'')),
												"currentLoanStatus" : (asset.currentLoanStatus == "Yes") ? 1: 0,
												"propertyIdentifierId" : asset.propertyIdentifierId,
												"currentLoanTenure" : angular.isNumber(asset.currentLoanTenure) ? asset.currentLoanTenure: parseInt(asset.currentLoanTenure.replace(/,/g,'')),
												"loantimeframe" : loanframe,
												"loanTypeId" : asset.loantypeid,
												"currentLoanOutstandingAmount" : angular.isNumber(asset.currentLoanOutstandingAmount) ? asset.currentLoanOutstandingAmount: parseInt(asset.currentLoanOutstandingAmount.replace(/,/g,'')),
												"currentEmi" : angular.isNumber(asset.currentEMI) ? asset.currentEMI: parseInt(asset.currentEMI.replace(/,/g,'')),
												"currentLender" : asset.CrLenderName,
												
											};
											
											var data = $.param({
												'userId' : parseInt(getcokkies
														.getUserId()),
												'userAssetData' : JSON
														.stringify(assetData),

											});
										 
											getassetservice.insertUserAsset(data).then(
															function successCallback(response) {
																
																if (response.data.Result == "Success") {
																	
																	$scope.sucess = true;
																	$scope.sucess_message = "Your changes have been saved successfully";
																	$scope.error = false;
																	$scope.loader = false;
																	$scope.projectDiv = false;
																	$scope.showDiv = false;
																	var responsePageNav = response.data.Page;
																	if(responsePageNav >= 1){
																		$('.personalLabel').css('pointer-events', 'all');
																		
																		localStorage.setItem('PAGE_COMPLETED',responsePageNav);
																		$rootScope.PAGE_COMPLETED = responsePageNav;
																		$scope.PAGE_COMPLETED = responsePageNav;
																		
																		
																		$(".navbar a")[2].style =( 'cursor', 'pointer' );
																		$(".navbar a")[2].title =""
																	}
																	else{
																		$('.personalLabel').css('pointer-events', 'none');
																	}
																	
																	localStorage.setItem("asset",response.data.userAssetData);
																	localStorage.setItem("assetFinancing",response.data.assetFinancingData);
																	
																	$scope.assetinfo = JSON.parse(localStorage.getItem("asset"));
																	
																	
																	$scope.Assets = JSON.parse(localStorage.getItem("assetFinancing"));
																	
																	$scope.Assets.projectZip = $scope.assetinfo.projectZip;
																	$scope.Assets.projectName = $scope.assetinfo.projectName;
																	$scope.Assets.preferedInterestRate = $scope.Assets.preferredInterestRate;
																	$scope.Assets.builderName = $scope.assetinfo.builderName;
																	$scope.Assets.reReCompliance = $scope.assetinfo.reracompliance;
																	$scope.Assets.assetArea = $scope.assetinfo.assetArea;
																	$scope.Assets.assetFloor = $scope.assetinfo.assetFloor;
																	$scope.Assets.assetValue = $scope.Assets.assetValue ? $scope.Assets.assetValue : 0;
																	$scope.Assets.loanValue = $scope.Assets.loanValue ? $scope.Assets.loanValue : 0;
																	$scope.Assets.loanTermId = $scope.Assets.loanTermId ? $scope.Assets.loanTermId : 0;
																	$scope.Assets.currentLoanTenure = $scope.Assets.currentLoanTenure ? $scope.Assets.currentLoanTenure : 0;
																	$scope.Assets.currentLoanBalanceTenure = $scope.Assets.currentLoanBalanceTenure ? $scope.Assets.currentLoanBalanceTenure : 0;
																	$scope.Assets.currentLoanOutstandingAmount = $scope.Assets.currentLoanOutstandingAmount ? $scope.Assets.currentLoanOutstandingAmount : 0;
																	$scope.Assets.currentEMI = $scope.Assets.currentEmi ? $scope.Assets.currentEmi : 0;
								
																	$scope.Assets.assetValue = $scope.Assets.assetValue == 0 ? '': $scope.Assets.assetValue.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
																	$scope.Assets.loanValue = $scope.Assets.loanValue == 0 ? '': $scope.Assets.loanValue.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
																	$scope.Assets.loanTermId = $scope.Assets.loanTermId == 0 ? '': $scope.Assets.loanTermId;
																	$scope.Assets.zipId = $scope.assetinfo.city;
																	$scope.Assets.currentLoanTenure = $scope.Assets.currentLoanTenure == 0 ? '': $scope.Assets.currentLoanTenure.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
																	$scope.Assets.currentLoanBalanceTenure = $scope.Assets.currentLoanBalanceTenure == 0 ? '': $scope.Assets.currentLoanBalanceTenure.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
																	$scope.Assets.currentLoanOutstandingAmount = $scope.Assets.currentLoanOutstandingAmount == 0 ? '': $scope.Assets.currentLoanOutstandingAmount.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
																	$scope.Assets.currentEMI = $scope.Assets.currentEMI == 0 ? '': $scope.Assets.currentEMI.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
																	$scope.Assets.CrLenderName = $scope.Assets.currentLender;
																	$scope.Assets.loantypeid = $scope.Assets.loanTypeId ? $scope.Assets.loanTypeId : 1;
																	$scope.Assets.loantime = parseInt($scope.Assets.loantimeframe);
																	
																	$scope.Assets.refnum = $scope.Assets.productTypeId;
																	
																	$scope.Assets.location = $scope.Assets.city;
																	$scope.Assets.assetType = $scope.assetinfo.assetType;
																	$scope.Assets.lenderName = $scope.assetinfo.originalLenderId;
																	$scope.Assets.approvedPlot = $scope.assetinfo.assetApprovedStatus == 1 ? "Yes": "No";
																	$scope.Assets.currentLoanStatus = $scope.Assets.currentLoanStatus == 1 ? "Yes": "No";
																	$scope.Assets.propertyIdentifierId = $scope.Assets.propertyIdentifierId;
																	
																	$scope.loader = false;
																	getZipInfo(
																			$scope.Assets.projectZip,
																			"first");
																	$scope
																			.errorremove();
																	$scope.assetsform
																			.$setPristine(true);

																	getassetservice
																			.getListOfBuilderProjectAndZipCode()
																			.then(
																					function successCallback(
																							response) {
																						$scope.builderprojects = JSON
																								.parse(response.data.Result);
																					},
																					function errorCallback(
																							response) {
																						// called
																						// asynchronously
																						// if
																						// an
																						// error
																						// occurs
																						// or
																						// server
																						// returns
																						// response
																						// with
																						// an
																						// error
																						// status.
																					});
																	var data = $
																			.param({
																				'userId' : parseInt(getcokkies
																						.getUserId())
																			});
																	getassetservice
																			.insertUserFinancial(
																					data)
																			.then(
																					function successCallback(
																							response) {

																					});
																	
																	if(e.target.className == "btn vcirclebtn pull-right savenxt"){
																		$state.go('index.customerPersonal');
																	}
																}

															},
															function errorCallback(
																	response) {
															});

										}
										
									} else {

										$scope.decimalcheck = /^\d+(?:\.\d+)?$/;
										if(asset.loantypeid == undefined){
											$scope.checkFields = {
												"loantypeid" : asset.loantypeid
											}
										}else 
										if (asset.refnum == 2) {

											$scope.checkFields = {
												"AssetValue" : asset.assetValue,
												"LoanTerm" : asset.loanTermId,
												"LoanValue" : asset.loanValue,
												"assetType" : asset.assetType,
												"propertyIdentifierId" : asset.propertyIdentifierId
												
											};
										} else if (asset.refnum == 6) {

											$scope.checkFields = {

												"AssetValue" : asset.assetValue,
												"LoanTerm" : asset.loanTermId,
												"LoanValue" : asset.loanValue,
												"userLenderName" : asset.lenderName,
												"currentLoanStatus" : asset.currentLoanStatus,
												"LoanTenure" : asset.currentLoanTenure,
												"LoanBalanceTenure" : asset.currentLoanBalanceTenure,
												"propertyIdentifierId" : asset.propertyIdentifierId
											};

										} else if (asset.refnum == 3) {

											$scope.checkFields = {
												"AssetValue" : asset.assetValue,
												"LoanTerm" : asset.loanTermId,
												"LoanValue" : asset.loanValue,
												"currentLoanStatus" : asset.currentLoanStatus,
												"propertyIdentifierId" : asset.propertyIdentifierId
											};

										} else if (asset.refnum == 4) {
											$scope.checkFields = {
												"AssetValue" : asset.assetValue,
												"LoanTerm" : asset.loanTermId,
												"LoanValue" : asset.loanValue,
												"approvedPlot" : asset.approvedPlot,
												"propertyIdentifierId" : asset.propertyIdentifierId
											}
										} else {

											$scope.checkFields = {
												"AssetValue" : asset.assetValue,
												"LoanTerm" : asset.loanTermId,
												"LoanValue" : asset.loanValue,
												"approvedPlot" : asset.approvedPlot,
												"propertyIdentifierId" : asset.propertyIdentifierId

											};
										}
										$scope.checkemptydata = [];
										_
												.every(
														_
																.keys($scope.checkFields),
														function(currentKey) {
															if ($scope.checkFields[currentKey] == ''
																	|| $scope.checkFields[currentKey] == undefined) {
																$scope.checkemptydata
																		.push(currentKey);
															}
															return $scope.checkemptydata;
														});
										if ($scope.checkemptydata.length > 0) {
											_
													.each(
															$scope.checkemptydata,
															function(value) {

																$('#' + value)
																		.addClass(
																				'redcolorinput');
															});
											$scope.error = true;
											$scope.error_message = "Highlighted Fields are mandatory.";
											$scope.sucess = false;
										} else if (parseInt(asset.assetValue
												.replace(/,/g, '')) < 600000) {
											$scope.error = true;
											$scope.error_message = "Asset value must be greater than six Lakhs";
											$scope.sucess = false;
										} else if (parseInt(asset.assetValue
												.replace(/,/g, '')) > 50000000) {
											$scope.error = true;
											$scope.error_message = "Asset value should not exceed 5,00,00,000";
											$scope.sucess = false;
										} else if (parseInt(asset.assetValue
												.replace(/,/g, '')) < parseInt(asset.loanValue
												.replace(/,/g, ''))) {
											$scope.error = true;
											$scope.error_message = "Asset value must be greater than loan value";
											$scope.sucess = false;
										} else if ($scope.eligibleLoanTerm < asset.loanTermId) {
											$scope.error = true;
											$scope.sucess_rest = false;
											$scope.error_message = "Maximum Eligible terms for is "+ $scope.eligibleLoanTerm;
										} else if (asset.assetValue == 0) {
											$scope.error = true;
											$scope.error_message = "AssetValue cannot be zero";
											$scope.success = false;
										} else if (asset.loanValue == 0) {
											$scope.error = true;
											$scope.error_message = "LoanValue cannot be zero";
											$scope.success = false;
										}
										else if (asset.loanTermId == 0) {
											$scope.error = true;
											$scope.error_message = "loanTerm cannot be zero";
											$scope.success = false;
										}
										
										else {
											var obj = _
													.findWhere(
															$scope.assetsProducts,
															{
																refnum : $scope.Assets.refnum
															})
											$scope.loader = true;
											var assetData = {
												"assetValue" : angular
														.isNumber(asset.assetValue) ? asset.assetValue
														: parseInt(asset.assetValue
																.replace(/,/g,
																		'')),
												"loanTermId" : parseInt(asset.loanTermId),
												"loanValue" : angular
														.isNumber(asset.loanValue) ? asset.loanValue
														: parseInt(asset.loanValue
																.replace(/,/g,
																		'')),
												"preferredInterestRate" : 0,
												"builderProjectId" : 1,
												"productTypeId" : obj.productTypeId,
												"assetType" : asset.assetType,
												"originalLenderId" : asset.lenderName,
												"assetApprovedStatus" : (asset.approvedPlot == "Yes") ? 1
														: 0,
												"currentLoanBalanceTenure" : angular
														.isNumber(asset.currentLoanBalanceTenure) ? asset.currentLoanBalanceTenure
														: parseInt(asset.currentLoanBalanceTenure
																.replace(/,/g,
																		'')),
												"currentLoanStatus" : (asset.currentLoanStatus == "Yes") ? 1
														: 0,
												"currentLoanTenure" : angular
														.isNumber(asset.currentLoanTenure) ? asset.currentLoanTenure
														: parseInt(asset.currentLoanTenure
																.replace(/,/g,
																		'')),
												"propertyIdentifierId" : asset.propertyIdentifierId,
												"currentLoanOutstandingAmount" : angular
														.isNumber(asset.currentLoanOutstandingAmount) ? asset.currentLoanOutstandingAmount
														: parseInt(asset.currentLoanOutstandingAmount
																.replace(/,/g,
																		'')),
												"currentEMI" : angular
														.isNumber(asset.currentEMI) ? asset.currentEMI
														: parseInt(asset.currentEMI
																.replace(/,/g,
																		'')),	
												"CrLenderName" : asset.CrLenderName
																		
											};

											var data = $.param({
												'userId' : parseInt(getcokkies
														.getUserId()),
												'userAssetData' : JSON
														.stringify(assetData)
											});

											getassetservice
													.insertUserAsset(data)
													.then(
															function successCallback(
																	response) {
																if (response.data.Result == "Success") {
																	$scope.sucess = true;
																	$scope.sucess_message = "Your changes have been saved successfully";
																	$scope.error = false;
																	$scope.loader = false;
																	var assetsNav=parseInt(localStorage.getItem('PAGE_COMPLETED'));
																	if(assetsNav >= 1){
																		$('.personalLabel').css('pointer-events', 'all');
																	}
																	else{
																		$('.personalLabel').css('pointer-events', 'none');
																	}
																	localStorage
																			.setItem(
																					"asset",
																					response.data.userAssetData);
																	localStorage
																			.setItem(
																					"assetFinancing",
																					response.data.assetFinancingData);
																	$scope.assetinfo = JSON
																			.parse(localStorage
																					.getItem("asset"));
																	$scope.Assets = JSON
																			.parse(localStorage
																					.getItem("assetFinancing"));
																	$scope.Assets.projectZip = $scope.assetinfo.projectZip;
																	$scope.Assets.projectName = $scope.assetinfo.projectName;
																	$scope.Assets.preferedInterestRate = $scope.Assets.preferredInterestRate;
																	$scope.Assets.builderName = $scope.assetinfo.builderName;
																	$scope.Assets.reReCompliance = $scope.assetinfo.reracompliance;
																	$scope.Assets.assetArea = $scope.assetinfo.assetArea;
																	$scope.Assets.assetFloor = $scope.assetinfo.assetFloor;
																	$scope.Assets.assetValue = $scope.Assets.assetValue ? $scope.Assets.assetValue : 0;
																	$scope.Assets.loanValue = $scope.Assets.loanValue ? $scope.Assets.loanValue : 0;
																	$scope.Assets.loanTermId = $scope.Assets.loanTermId ? $scope.Assets.loanTermId : 0;
																	$scope.Assets.currentLoanTenure = $scope.Assets.currentLoanTenure ? $scope.Assets.currentLoanTenure : 0;
																	$scope.Assets.currentLoanBalanceTenure = $scope.Assets.currentLoanBalanceTenure ? $scope.Assets.currentLoanBalanceTenure : 0;
																	$scope.Assets.currentLoanOutstandingAmount = $scope.Assets.currentLoanOutstandingAmount ? $scope.Assets.currentLoanOutstandingAmount : 0;
																	$scope.Assets.currentEMI = $scope.Assets.currentEmi ? $scope.Assets.currentEmi : 0;
																	
																	$scope.Assets.assetValue = $scope.Assets.assetValue == 0 ? '': $scope.Assets.assetValue.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
																	$scope.Assets.loanValue = $scope.Assets.loanValue == 0 ? '': $scope.Assets.loanValue.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
																	$scope.Assets.loanTermId = $scope.Assets.loanTermId == 0 ? '': $scope.Assets.loanTermId;
																	$scope.Assets.zipId = $scope.assetinfo.city;
																	$scope.Assets.lenderName = $scope.Assets.lenderName;
																	$scope.Assets.currentLoanTenure = $scope.Assets.currentLoanTenure == 0 ? '': $scope.Assets.currentLoanTenure.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
																	$scope.Assets.currentLoanBalanceTenure = $scope.Assets.currentLoanBalanceTenure == 0 ? '': $scope.Assets.currentLoanBalanceTenure.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
																	$scope.Assets.currentLoanOutstandingAmount = $scope.Assets.currentLoanOutstandingAmount == 0 ? '': $scope.Assets.currentLoanOutstandingAmount.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
																	$scope.Assets.currentEMI = $scope.Assets.currentEMI == 0 ? '': $scope.Assets.currentEMI.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
																	$scope.Assets.CrLenderName = $scope.Assets.currentLender;
																	$scope.Assets.approvedPlot = $scope.assetinfo.assetApprovedStatus == 1 ? "Yes": "No";
																	$scope.Assets.currentLoanStatus = $scope.Assets.currentLoanStatus == 1 ? "Yes": "No";
																	$scope.Assets.location = $scope.Assets.city;
																	$scope.Assets.loantime = parseInt($scope.Assets.loantimeframe);
																	$scope.Assets.refnum = $scope.Assets.productTypeId;
																	$scope.Assets.assetType = $scope.Assets.assetType;
																	$scope.loader = false;
																	$scope.errorremove();

																	

																	$scope.assetsform
																			.$setPristine(true);
																	var data = $
																			.param({
																				'userId' : parseInt(getcokkies
																						.getUserId())
																			});
																	getassetservice
																			.insertUserFinancial(
																					data)
																			.then(
																					function successCallback(
																							response) {
																					});
																}
																if(e.target.className == "btn vcirclebtn pull-right savenxt"){
																	$state.go('index.customerPersonal');
																}
															},
															function errorCallback(
																	response) {
															});
										}
									}
								}
						// get City List 
								$scope.savenext = function(asset,e) {
									var val = $scope.save(asset,e);
								}
								//Method added by Priyaraj on 6th February 2023 in order to get producttypes based on radio option selection
								$scope.getProductTypes = function() {
									//We need to show all producttypes for 3
									if ($scope.Assets.loantypeid == 3) {
										$scope.assetsProducts = $scope.filteredProductsForLoanTypeThree;
									}
									// As we do not need to keep Commercial and Residential product description, it will not be displayed when loantypeid = 1 or 2
									if ($scope.Assets.loantypeid == 1 || $scope.Assets.loantypeid == 2) {
										$scope.assetsProducts = $scope.filteredProductsForLoanTypesOneAndTwo;
									}
								} 
								$scope.next = function(asset) {
									var check = $scope.assetsform.$dirty; // $scope.changesfields(asset);
									if (!check) {
										$state.go('index.customerPersonal');
									} else {
										$scope.error = true;
										var r = confirm("Current page has unsaved data. Are you sure you want to proceed?");
										if (r == true) {
											$state.go('index.customerPersonal');
										}
										$scope.error_message = "Highlighted Field values have been modified";
										$('input.ng-dirty').addClass(
												'redcolorinput');
										$('select.ng-dirty').addClass(
												'redcolorinput');
										$scope.sucess = false;
										
									} 
								}
								$scope.nextPage = function() {
									$state.go('index.customerPersonal');
								}
								$scope.reset = function() {		 
		 							$('select.ng-dirty').removeClass(
											'redcolorinput');
									$('input.ng-dirty').removeClass(
											'redcolorinput');
									$("#ProjectZip").removeClass(
											'redcolorinput');
									$("#location").removeClass('redcolorinput');
									$("#assetword").css('display', 'none');
									$("#loanvalword").css('display', 'none');
									$("#crloanvalword").css('display', 'none');
									$("#btassetword").css('display', 'none');
									$("#cEMIassetword").css('display', 'none');
									$scope.sucess = true;
									$scope.sucess_message = "Your changes have been reset successfully";
									$scope.error = false;
									$scope.assetsform.$setPristine(true);
									$scope.assetinfo = JSON.parse(localStorage.getItem("asset"));
									$scope.Assets = JSON.parse(localStorage.getItem("assetFinancing"));
									$scope.Assets.assetValue = $scope.Assets.assetValue ? $scope.Assets.assetValue : 0;
									$scope.Assets.loanValue = $scope.Assets.loanValue ? $scope.Assets.loanValue : 0;
									$scope.Assets.loanTermId = $scope.Assets.loanTermId ? $scope.Assets.loanTermId : 0;
									$scope.Assets.currentLoanTenure = $scope.Assets.currentLoanTenure ? $scope.Assets.currentLoanTenure : 0;
									$scope.Assets.currentLoanBalanceTenure = $scope.Assets.currentLoanBalanceTenure ? $scope.Assets.currentLoanBalanceTenure : 0;
									$scope.Assets.currentLoanOutstandingAmount = $scope.Assets.currentLoanOutstandingAmount ? $scope.Assets.currentLoanOutstandingAmount : 0;
									$scope.Assets.currentEMI = $scope.Assets.currentEmi ? $scope.Assets.currentEmi : 0;
									$scope.Assets.currentLoanOutstandingAmount = $scope.Assets.currentLoanOutstandingAmount ? $scope.Assets.currentLoanOutstandingAmount.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",") : '';
									$scope.Assets.currentEMI = $scope.Assets.currentEmi ? $scope.Assets.currentEmi.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",") : '';
									$scope.Assets.CrLenderName = $scope.Assets.CrLenderName;
									//$scope.Assets.loantypeid = $scope.Assets.loanTypeId;
									$scope.Assets.loantypeid = $scope.Assets.loanTypeId ? $scope.Assets.loanTypeId : 1;
									$scope.Assets.projectZip = $scope.assetinfo.projectZip;
									$scope.Assets.projectName = $scope.assetinfo.projectName;
									$scope.Assets.preferedInterestRate = $scope.Assets.preferredInterestRate;
									$scope.Assets.builderName = $scope.assetinfo.builderName;
									$scope.Assets.reReCompliance = $scope.assetinfo.reracompliance;
									$scope.Assets.assetArea = $scope.assetinfo.assetArea;
									$scope.Assets.assetFloor = $scope.assetinfo.assetFloor;
									$scope.Assets.assetValue = $scope.Assets.assetValue == 0 ? '': $scope.Assets.assetValue.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
									$scope.Assets.loanValue = $scope.Assets.loanValue == 0 ? '': $scope.Assets.loanValue.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
									$scope.Assets.loanTermId = $scope.Assets.loanTermId == 0 ? '': $scope.Assets.loanTermId;
									$scope.Assets.currentLoanTenure = $scope.Assets.currentLoanTenure == 0 ? '': $scope.Assets.currentLoanTenure.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
									$scope.Assets.currentLoanBalanceTenure = $scope.Assets.currentLoanBalanceTenure == 0 ? '': $scope.Assets.currentLoanBalanceTenure.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
									$scope.Assets.currentLoanOutstandingAmount = $scope.Assets.currentLoanOutstandingAmount == 0 ? '': $scope.Assets.currentLoanOutstandingAmount.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
									$scope.Assets.currentEMI = $scope.Assets.currentEMI == 0 ? '': $scope.Assets.currentEMI.toLocaleString('en-IN').replace(/\B(?=(\d{3})+(?!\d))/g,",");
									$scope.Assets.CrLenderName = $scope.Assets.currentLender;
									//$scope.Assets.loantypeid = $scope.Assets.loanTypeId;
									$scope.Assets.zipId = $scope.assetinfo.city;
									$scope.Assets.refnum = $scope.Assets.productTypeId;
									$scope.Assets.loantime = parseInt($scope.Assets.loantimeframe);
									$scope.Assets.lenderName = $scope.assetinfo.originalLenderId;
									$scope.Assets.location = $scope.Assets.city;
									$scope.Assets.approvedPlot = $scope.assetinfo.assetApprovedStatus == 1 ? "Yes": "No";
									$scope.Assets.currentLoanStatus = $scope.Assets.currentLoanStatus == 1 ? "Yes": "No";
									$scope.loader = false;
									$scope.emptyAssetsValue1(); //to set builder and project name disabled
								}
								$scope.validatest = function(asset) {

									if (asset < 600000) {
										$scope.error = true;
										$scope.error_message = "Asset value must be greater than six Lakhs";
										$scope.sucess = false;
									} else if (asset > 50000000) {
										$scope.error = true;
										$scope.error_message = "Asset value must be less than Five Crores";
										$scope.sucess = false;
									}
								}
								$scope.changesfields = function(asset) {
									$scope.omitdata = [];
									var Assets = JSON.parse(localStorage	
											.getItem("Assets"));
									if (Assets) {
										$scope.changesvalue = {
											"ProjectZip" : asset.projectZip,
											"BuilderName" : asset.builderName,
											"ProjectName" : asset.projectName,
											"AssetValue" : asset.assetValue,
											"AssetArea" : asset.assetArea,
											"AssetFloor" : asset.assetFloor,
											"LoanTerm" : asset.loanTermId,
											"LoanValue" : asset.loanValue,
											"PreferredInterestRate" : asset.preferedInterestRate,
											"reRaCompliance" : asset.reReCompliance,
											"location" : asset.location
										};
										Assets = Assets[0];
										$scope.orignalData = {
											"ProjectZip" : Assets.projectZip,
											"BuilderName" : Assets.builderName,
											"ProjectName" : Assets.projectName,
											"AssetValue" : Assets.assetValue
													.toString()
													.replace(
															/\B(?=(\d{3})+(?!\d))/g,
															","),
											"AssetArea" : Assets.assetArea,
											"AssetFloor" : Assets.assetFloor,
											"LoanTerm" : Assets.loanTermId,
											"LoanValue" : Assets.loanValue
													.toString()
													.replace(
															/\B(?=(\d{3})+(?!\d))/g,
															","),
											"PreferredInterestRate" : Assets.preferredInterestRate,
											"reRaCompliance" : Assets.reReCompliance,
											"location" : asset.location
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

											return true;
										} else {
											return false;
										}
									} else {
										return false;
									}
								}
								$scope.errorremove = function() {
									$("#city").removeAttr("multiple");
									_.each($scope.omitdata, function(value) {
										$('#' + value).removeClass(
												'redcolorinput');
									})
									_.each($scope.checkemptydata, function(
											value) {
										$('#' + value).removeClass(
												'redcolorinput');
									})
									$scope.checkemptydata = [];
									$scope.omitdata = [];
								}
								$scope.loanvaluefunc = function(event, val) {
									$("#loanvalword").css('display', 'block');
									var loanval = commaSeparatedNumeric(event,
											val);									
									$scope.Assets.loanValue = loanval;									
								}
								$scope.assetvaluefunc = function(event, val) {
									var assetval = commaSeparatedNumeric(event,
											val);
									$scope.Assets.assetValue = assetval;
								}

								// Function for amount value in indian format
								String.prototype.replaceAll = function(search,
										replacement) {
									var target = this;
									return target.replace(new RegExp(search,
											'g'), replacement);
								};

								function commaSeparatedNumeric(event, val) {
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
								}
								;

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

								$scope.assetsnumericOnly = function(event, val) {
									if (val) {
										// skip for arrow keys
										if (event.which >= 37
												&& event.which <= 40) {
											event.preventDefault();
										}
										var $this = $(this);
										var num = val.replace(/,/gi, "").split(
												"").reverse().join("");

										var num2 = RemoveRougeChar(num.replace(
												/[^0-9]+/g, '').replace(
												/(.{3})/g, "$1,").split("")
												.reverse().join(""));
										$scope.Assets.assetValue = num2;
									}
								};
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
								$scope.loannumericOnly = function(event, val) {
									if (val) {
										// skip for arrow keys
										if (event.which >= 37
												&& event.which <= 40) {
											event.preventDefault();
										}
										var $this = $(this);
										var num = val.replace(/,/gi, "").split(
												"").reverse().join("");

										var num2 = RemoveRougeChar(num.replace(
												/[^0-9]+/g, '').replace(
												/(.{3})/g, "$1,").split("")
												.reverse().join(""));
										$scope.Assets.loanValue = num2;
									}
								};
								$scope.assetareanumericOnly = function(event,
										val) {
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
									$scope.Assets.assetArea = num2;
								};

								$scope.assetareanumericAddOnly = function(
										event, val) {
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
									$scope.Add.assetArea = num2;
								};
								$scope.assettermnumericOnly = function(event,
										val) {
									$("#assetword").css('display', 'block');
						//			("#loanvalword").css('display', 'block');
						//			$("#crloanvalword").css('display', 'block');
						//			$("#btassetword").css('display', 'block');
						//			$("#cEMIassetword").css('display', 'block');
									if (val) {
										// skip for arrow keys
										if (event.which >= 37
												&& event.which <= 40) {
											event.preventDefault();
										}
										var $this = $(this);
										var num = val.replace(/,/gi, "").split(
												"").reverse().join("");

										var num2 = RemoveRougeChar(num.replace(
												/[^0-9]+/g, '').replace(
												/(.{3})/g, "$1,").split("")
												.reverse().join(""));
										$scope.Assets.loanTermId = num2;
									}
								};

								$scope.assetfloornumericOnly = function(event,
										val) {
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
									$scope.Assets.assetFloor = num2;
								};

								$scope.loanStatusnumericOnly = function(event,
										val) {
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
									$scope.Assets.currentLoanStatus = num2;
								};
								$scope.loanTenurenumericOnly = function(event,
										val) {
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
									$scope.Assets.currentLoanTenure = num2;
								};
								$scope.loanBalanceTenurenumericOnly = function(
										event, val) {
									// skip for arrow keys
									$("#btassetword").css('display', 'block');
									if (event.which >= 37 && event.which <= 40) {
										event.preventDefault();
									}
									var $this = $(this);
									var num = val.replace(/,/gi, "").split("")
											.reverse().join("");

									var num2 = RemoveRougeChar(num.replace(
											/[^0-9]+/g, '').replace(/(.{3})/g,
											"$1,").split("").reverse().join(""));
									$scope.Assets.currentLoanBalanceTenure = num2;
								};
								$scope.currentLoanOutstandingAmountnumericOnly = function(
										event, val) {
									// skip for arrow keys
									$("#crloanvalword").css('display', 'block');
									
									if (event.which >= 37 && event.which <= 40) {
										event.preventDefault();
									}
									var $this = $(this);
									var num = val.replace(/,/gi, "").split("")
											.reverse().join("");

									var num2 = RemoveRougeChar(num.replace(
											/[^0-9]+/g, '').replace(/(.{3})/g,
											"$1,").split("").reverse().join(""));
									$scope.Assets.currentLoanOutstandingAmount = num2;
								};
								$scope.currentEMInumericOnly = function(
										event, val) {
									// skip for arrow keys
									$("#cEMIassetword").css('display', 'block');
									
									if (event.which >= 37 && event.which <= 40) {
										event.preventDefault();
									}
									var $this = $(this);
									var num = val.replace(/,/gi, "").split("")
											.reverse().join("");
									
									var num2 = RemoveRougeChar(num.replace(
											/[^0-9]+/g, '').replace(/(.{3})/g,
											"$1,").split("").reverse().join(""));
									
									$scope.Assets.currentEMI = num2;
								};
								$scope.removetext = function(val) {
									$scope.Assets.preferedInterestRate = val
											.replace(/[^\d.-]/g, '');
								}
								$scope.removetextzip = function(val) {
									$scope.Assets.projectZip = val.replace(
											/[^0-9\/]+/g, "")
											.replace(/\\/g, '');
								}
								$scope.removetextzipAdd = function(val) {
									$scope.Add.projectZip = val.replace(
											/[^0-9\/]+/g, "")
											.replace(/\\/g, '');
								}
								function RemoveRougeChar(convertString) {

									if (convertString.substring(0, 1) == ",") {

										return convertString.substring(1,
												convertString.length)
									}
									return convertString;
								}
								$scope.$watch('assetsform.$dirty', function(
										neeval, oldval) {
									if (neeval == true) {
										$scope.true_reset = true;
										$scope.sucess = false;
										$scope.error = false;
									} else {
										$scope.true_reset = false;
									}
								}, true);
								$scope.convertMonthNumberToWords = function(amount) {
									if (amount) {
										
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
										var number = atemp[0].split(",").join(
												"");
										var n_length = number.length;
										var words_string = "";
										if (n_length <= 9) {
											var n_array = new Array(0, 0, 0, 0,
													0, 0, 0, 0, 0);
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
														|| (i == 0
																&& value != 0 && n_array[i + 1] == 0)) {
													words_string += "Crores ";
												}
												if ((i == 3 && value != 0)
														|| (i == 2
																&& value != 0 && n_array[i + 1] == 0)) {
													words_string += "Lakhs ";
												}
												if ((i == 5 && value != 0)
														|| (i == 4
																&& value != 0 && n_array[i + 1] == 0)) {
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
											words_string = words_string.split("  ").join(" ");
											words_string += " Months";
										}
										
										return words_string;
									} else {
										return "";
									}
								}
								$scope.convertNumberToWords = function(amount) {
									if (amount) {
										
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
										var number = atemp[0].split(",").join(
												"");
										var n_length = number.length;
										var words_string = "";
										if (n_length <= 9) {
											var n_array = new Array(0, 0, 0, 0,
													0, 0, 0, 0, 0);
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
														|| (i == 0
																&& value != 0 && n_array[i + 1] == 0)) {
													words_string += "Crores ";
												}
												if ((i == 3 && value != 0)
														|| (i == 2
																&& value != 0 && n_array[i + 1] == 0)) {
													words_string += "Lakhs ";
												}
												if ((i == 5 && value != 0)
														|| (i == 4
																&& value != 0 && n_array[i + 1] == 0)) {
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
											words_string = words_string.split("  ").join(" ");
										}
										
										return words_string;
									} else {
										return "";
									}
								}
								$scope.loantyperadio = function(index){
									
									if ($('input[name=optradio0]:checked').val() == 1 || $('input[name=optradio0]:checked').val() == 3 || $('input[name=optradio0]:checked').val() == 4) {
										
								 		$('#CrLoanValue').val(0);
								 		$('#CrLoanValue').removeClass(
										'redcolorinput');
								 		$scope.Assets.currentLoanOutstandingAmount=0;
								 		$('#currentLoanTerm').val(0);
										$('#currentLoanTerm').removeClass(
										'redcolorinput');
										$scope.Assets.currentLoanBalanceTenure=0;
										$('#CrEMI').val(0);
										$('#CrEMI').removeClass(
										'redcolorinput');
										$scope.Assets.currentEMI=0;
										$('#loanlender').removeClass(
										'redcolorinput');
										$('#loanlender').val("");
										$scope.Assets.CrLenderName=null;
										$("#crloanvalword").val("0");
										$("#btassetword").val("0");
										$("#cEMIassetword").val("0");
										$("#crloanvalword").css('display', 'none');
										$("#btassetword").css('display', 'none');
										$("#cEMIassetword").css('display', 'none');
										
									}else {
										
										$('#CrLoanValue').removeAttr('disabled');
										$('#CrLoanValue').val("");
										$('#currentLoanTerm').removeAttr('disabled');
										$('#currentLoanTerm').val("");
										$('#CrEMI').removeAttr('disabled');
										$('#CrEMI').val("");
										$('#loanlender').val("")
										$('#loanlender').removeAttr('disabled');
									//	$("#crloanvalword").css('display', 'none');
									//	$("#btassetword").css('display', 'none');
									//	$("#cEMIassetword").css('display', 'none');
							
									}
									if($scope.Assets.loantypeid== 2||$scope.Assets.loantypeid== 3 ||$scope.Assets.loantypeid== 4){
										
										$scope.Assets.propertyIdentifierId = '1';
										$('#PropertyIdentifier').prop("disabled",true);
									}
									
								}
								$scope.propradio = function(index) {
										if ($('input[name=optradio1]:checked').val() == 1) {
											
											if ($scope.Assets.refnum == '1' ) {
												
												$("#location").removeAttr('disabled');
											} else {
												
												$("#location").removeAttr('disabled');
												$('#addBuilderName').prop("disabled", true);
												$('#addProjectName').prop("disabled", true);
											}
											
										} else {		
										
										$('#addBuilderName').attr('value',"");
										$('#addProjectName').attr('value',"");
							            $('#purchaseVal').val('number:3');
								    	$scope.Assets.refnum = "3";
										$scope.Assets.builderName = '';
										$scope.Assets.projectName = '';
										$('#purchaseVal').removeClass('redcolorinput');
										$("#location").removeAttr('disabled');
										$('#addBuilderName').prop("disabled",true);
										$('#addProjectName').prop("disabled",true);
										$('#addProjectName').val("");
										$('#addBuilderName').val("");
										$('select#location').attr( "value","00" );
										
									}
								}
								$scope.emptyAssetsValue = function() {
									
									if($scope.Assets.loantypeid== 1 ||$scope.Assets.loantypeid== 3 ||$scope.Assets.loantypeid== 4 ){
										$('#CrLoanValue').val(0);
								 		$scope.Assets.currentLoanOutstandingAmount=0;
										$('#currentLoanTerm').val(0);
										$scope.Assets.currentLoanBalanceTenure=0;
										$('#CrEMI').val(0);
										$scope.Assets.currentEMI=0;
										$('#loanlender').val(0);
										$scope.Assets.CrLenderName=null;
									}
									else{
										
									//	$scope.Assets.currentLoanOutstandingAmount=null;
										
									//	$scope.Assets.currentLoanBalanceTenure=null;
										
									//	$scope.Assets.currentEMI=null;
										
									//	$scope.Assets.CrLenderName=null;
									}
									if($scope.Assets.loantypeid== 2||$scope.Assets.loantypeid== 3 ||$scope.Assets.loantypeid== 4){
										$scope.Assets.propertyIdentifierId = '1';
										$('#PropertyIdentifier').prop("disabled",true);
									}
									
									if ($scope.Assets.refnum == '1' && $scope.Assets.propertyIdentifierId == '1') {
										$("#location").removeAttr('disabled');
										if(!$scope.Assets.builderName){
											$('#addBuilderName').prop("disabled",true);
										}else{
											$('#addBuilderName').prop("disabled",false);
										}
										if($('#addBuilderName').val() != "" && !$scope.Assets.projectName){
											$('#addProjectName').prop("disabled",true);
										}else{
											$('#addProjectName').prop("disabled",false);
										}
									} else if (($scope.Assets.refnum == '2' || $scope.Assets.refnum == '3' || $scope.Assets.refnum == '4' || $scope.Assets.refnum == '5') && $scope.Assets.propertyIdentifierId == '1') { 
										$('#addProjectName').val("");
										$('#addBuilderName').val("");
										$('select#location').val("" );
										$("#location").removeAttr('disabled');
										$('#addBuilderName').prop("disabled",true);
										$('#addProjectName').prop("disabled",true);
									}else{
										$('#addProjectName').val("");
										$('#addBuilderName').val("");
										$('select#location').val("" );
										$("#location").removeAttr('disabled');
										$('#addBuilderName').prop("disabled",true);
										$('#addProjectName').prop("disabled",true);
									}

								}
								$scope.emptyAssetsValue1 = function() {
									if ($scope.Assets.refnum == '1' && $scope.Assets.propertyIdentifierId == '1') {
										$("#location").removeAttr('disabled');
										$('#addBuilderName').prop("disabled",false);
										/* if(!$scope.Assets.builderName){
											$('#addBuilderName').prop("disabled",true);
										}else{
											$('#addBuilderName').prop("disabled",false);
										}*/
										
									} else if (($scope.Assets.refnum == '2' || $scope.Assets.refnum == '3' || $scope.Assets.refnum == '4' || $scope.Assets.refnum == '5') && $scope.Assets.propertyIdentifierId == '1') { 
										$('#addProjectName').val("");
										$('#addBuilderName').val("");
										$('select#location').val("" );
										$("#location").removeAttr('disabled');
										$('#addBuilderName').prop("disabled",true);
										$('#addProjectName').prop("disabled",true);
									}else{
										$('#addProjectName').val("");
										$('#addBuilderName').val("");
										$('select#location').val("" );
										$("#location").removeAttr('disabled');
										$('#addBuilderName').prop("disabled",true);
										$('#addProjectName').prop("disabled",true);
									}

								}
								$scope.locationSelectValue = function() {
									
									if ($scope.Assets.location != undefined || $scope.Assets.location != "" || $scope.Assets.location != null) {
										$('#addBuilderName').prop("disabled",false);
									} else {
									}
									
								
								}
								$scope.setBuilderNameValue = function() {
									var city = $('#location').val();
									$scope.buildid = $("#addBuilderName1 option[value='"+ $.trim($('#addBuilderName').val()) + "']").attr('id');
									if($scope.buildid == undefined){
										$scope.buildid = $('#addBuilderName').attr('value');
									}
									
									if ($scope.Assets.builderName != undefined || $scope.Assets.builderName != "" || $scope.Assets.builderName != null) {
										var data = $.param({
											'sessionId' : parseInt(getcokkies.getsessionId()),
											'builderId' : $scope.buildid,
											'location': city
										});
										getassetservice.getProjectName(data)
												.then(
														function successCallback(response) {
															//$scope.projects = JSON.parse(response.data.Result);
															
															var nativedatalist = !!('list' in document.createElement('input')) && 
															!!(document.createElement('datalist') && window.HTMLDataListElement);
													
														
														if (!nativedatalist) {
															$('input[list]').each(function () {
																var availableTags = $('#' + $(this).attr("list")).children().map(function () {
																	return this.value;
																}).get();
																$(this).autocomplete({ source: availableTags });
															});
														}
														$scope.projects = JSON.parse(response.data.Result);
														},
														function errorCallback(response) {
														});
									} else {
										$('#addProjectName').prop("disabled",true);
									}
								}
								
								$scope.activateProj = function(e){
									$('#addProjectName').prop("disabled",false);
									var city = $('#location').val();
									var data = city;
									$scope.change(data,e);
									
									}
								if ($scope.Assets.propertyIdentifierId == '1' ) {
									$("#purchaseVal option[value='number:3']").hide();
								} else {
									$("#purchaseVal option[value='number:3']").show();
								}
								$(document).ready(function() {
									
									$scope.emptyAssetsValue();
								});
								$scope.setCityName=function(city){
										$scope.cityMinChar=" ";
										var cityName=city;
										 var data = 'key='+ cityName;
										getassetservice
												.getListOfCities(data)
												.then(
														function successCallback(
																response) {
															var resData=response.data.Result;
															$scope.citysArray = JSON.parse(resData);
															 $scope.destArray = _.uniq($scope.citysArray, function(x) {
											                    });
															$scope.loader = false;
														},
														function errorCallback(
																response) {
													});
													}
								$scope.setcityBasedBuilder=function(name){
								}
								$scope.citty = function(){
									if($scope.Assets.refnum == '1'){
										$("#addBuilderName").removeAttr('disabled');
									}else{
										$("#addBuilderName").attr('disabled');	
									}
									//locationSelectValue();
								}
								//var resData;
								$scope.change=function(data,e){
									$('#addBuilderName').attr('value','');
									var cityName=data;
									
									if(seletedCity == "" || seletedCity != data){

										seletedCity = data;
										var data = 'city='+ cityName;
									getassetservice.getListOfCitiesBasedBuilderName(data)
											.then(
													function successCallback(response) {	
													var resData1 = response.data.Result;
													var	resData = JSON.parse(resData1);
													
													$scope.cityBasedBuilderName = resData;
														
														var nativedatalist = !!('list' in document.createElement('input')) && 
														!!(document.createElement('datalist') && window.HTMLDataListElement);
												
													if (!nativedatalist) {
														$('input[list]').each(function () {
															var availableTags = $('#' + $(this).attr("list")).children().map(function () {
																return this.value;
															}).get();
															$(this).autocomplete({ source: availableTags });
														});
													}
													if(e != undefined){
															if(e.type == "keyup"){
															$scope.Assets.projectName= '';
															$('#addProjectName').val("");
															}else{
																
															}
														}
													},
													function errorCallback(response) {
														
													});
									}
								}
								
								
								
								

								function getBrowserInfo(){				
								var check = false;
								  (function(a){if(/(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows ce|xda|xiino/i.test(a)||/1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(a.substr(0,4))) check = true;})(navigator.userAgent||navigator.vendor||window.opera);
								  return check; 							 						
								}

							} ]);
})();