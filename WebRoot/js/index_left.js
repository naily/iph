$(document).ready(function() {
			var data1 = [{
						"text" : "武汉观测站",
						"expanded" : true,
						"children" : [{
									"text" : "1946年"
								}, {
									"text" : "1947年"
								}]
					}];
			$("#mytree1").omTree({
						dataSource : data1,
						showCheckbox : false,
						showIcon : false
					});

		});