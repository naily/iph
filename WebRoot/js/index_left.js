$(document).ready(function() {
			/*var data1 = [{
						"text" : "武汉观测站",
						"expanded" : true,
						"children" : [{
									"text" : "1946年"
								}, {
									"text" : "1947年"
								}]
					}];*/
					var pgtTreeData;		
					var pgt_data = {
								url : 'qt/indexLeftPGT.do',
								params : {
								dataTable : 'T_IRONOGRAM'
							},
							callback : function(json) {	
								if(json.success){
									pgtTreeData = json.data;
								}
								$("#mytree1").omTree({
									dataSource : pgtTreeData,
									showCheckbox : false,
									showIcon : false,
									onClick: function(node,event) {
					                   // $("#event_name").html("onClick事件");
					                    //$("#event_target").html(node.text);
										location.href='qt/listPGT.do';
					                }
								});
							}
						}
						ajaxpost(pgt_data);
					
			

		});