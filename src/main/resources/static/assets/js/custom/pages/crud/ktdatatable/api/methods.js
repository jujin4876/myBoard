"use strict";
// Class definition

var KTDefaultDatatableDemo = function() {
	// Private functions

	// basic demo
	var demo = function() {

		var options = {
			// datasource definition
			data: {
				type: 'remote',
				source: {
					read: {
						url: '/api/boards',
					},
				},
				pageSize: 20, // display 20 records per page
				serverPaging: true,
				serverFiltering: true,
				serverSorting: true,
			},

			// layout definition
			layout: {
				scroll: true, // enable/disable datatable scroll both horizontal and vertical when needed.
				height: 550, // datatable's body's fixed height
				footer: false, // display/hide footer
			},

			// column sorting
			sortable: true,

			pagination: true,

			search: {
				input: $('#kt_datatable_search_query'),
				key: 'generalSearch'
			},

			// columns definition
			columns: [
				{
					field: 'id',
					title: '#',
					sortable: false,
					type: 'number',
					width: 30,
					selector: true,
					textAlign: 'center',
					template: function(row) {
						return 1;
					},
				},
				{
					field: 'id',
					title: '번호',
					type: 'number',
					width: 30,
				}, {
					field: 'writer',
					title: '작성자',
				}, {
					field: 'createdDate',
					title: '등록일',
					type: 'date',
					format: 'YYYY-MM-DD',
				}, {
					field: 'hits',
					type: 'number',
					width: 30,
					title: '조회 수',
				}],

		};

		var datatable = $('#kt_datatable').KTDatatable(options);

		// both methods are supported
		// datatable.methodName(args); or $(datatable).KTDatatable(methodName, args);

		$('#kt_datatable_destroy').on('click', function() {
			// datatable.destroy();
			$('#kt_datatable').KTDatatable('destroy');
		});

		$('#kt_datatable_init').on('click', function() {
			datatable = $('#kt_datatable').KTDatatable(options);
		});

		$('#kt_datatable_reload').on('click', function() {
			// datatable.reload();
			$('#kt_datatable').KTDatatable('reload');
		});

		$('#kt_datatable_sort_asc').on('click', function() {
			datatable.sort('Status', 'asc');
		});

		$('#kt_datatable_sort_desc').on('click', function() {
			datatable.sort('Status', 'desc');
		});

		// get checked record and get value by column name
		$('#kt_datatable_get').on('click', function() {
			// select active rows
			datatable.rows('.datatable-row-active');
			// check selected nodes
			if (datatable.nodes().length > 0) {
				// get column by field name and get the column nodes
				var value = datatable.columns('CompanyName').nodes().text();
				console.log(value);
			}
		});

		// record selection
		$('#kt_datatable_check').on('click', function() {
			var input = $('#kt_datatable_check_input').val();
			datatable.setActive(input);
		});

		$('#kt_datatable_check_all').on('click', function() {
			// datatable.setActiveAll(true);
			$('#kt_datatable').KTDatatable('setActiveAll', true);
		});

		$('#kt_datatable_uncheck_all').on('click', function() {
			// datatable.setActiveAll(false);
			$('#kt_datatable').KTDatatable('setActiveAll', false);
		});

		$('#kt_datatable_hide_column').on('click', function() {
			datatable.columns('ShipDate').visible(false);
		});

		$('#kt_datatable_show_column').on('click', function() {
			datatable.columns('ShipDate').visible(true);
		});

		$('#kt_datatable_remove_row').on('click', function() {
			datatable.rows('.datatable-row-active').remove();
		});

		$('#kt_datatable_search_status').on('change', function() {
			datatable.search($(this).val().toLowerCase(), 'Status');
		});

		$('#kt_datatable_search_type').on('change', function() {
			datatable.search($(this).val().toLowerCase(), 'Type');
		});

		$('#kt_datatable_search_status, #kt_datatable_search_type').selectpicker();

	};

	return {
		// public functions
		init: function() {
			demo();
		},
	};
}();

jQuery(document).ready(function() {
	KTDefaultDatatableDemo.init();
});
