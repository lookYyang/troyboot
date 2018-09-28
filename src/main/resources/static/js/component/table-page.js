Vue.component('table-page', {
    template: '<div>' +
                '<i-table border stripe ref="selection" :loading="loading" :columns="columns" :data="tableData" @on-selection-change="selChange"></i-table>' +
                '<Page show-total v-if="showPages" :total="pageTotal" :page-size="offset" @on-change="pageChange" style="float:left;margin-top:10px;"/>' +
              '</div>',
    data: function () {
        return {
            tableData: [],
            loading: false,
            pageTotal: 0,
            selectRows: [],
            // 分页需要的参数
            limit: 1,
            offset: ''
        }
    },
    props: {
        url: {
            type: String,
            required: true
        },
        columns: {
            type: Array,
            required: true
        },
        // 是否显示分页，表格默认分页
        showPages: {
            type: Boolean,
            default: true
        },
        offset: {
            type: Number,
            default: 15
        },
        searchParams: {
            default(){
                return {};
            }
        }
    },
    mounted(){
        this.initTable();
    },
    computed: {

    },
    methods: {
        initTable() {
            this.loadData();
        },

        pageChange(limit) {
            this.limit = limit;
            this.loadData();
        },

        selChange(selection) {
            let rowsId = [];
            _.forEach(selection, function(row) {
                rowsId.push(row.id);
            });
            this.selectRows = rowsId;
        },

        loadData() {
            var self = this;
            // 如果需要分页，把分页数据添加到参数中
            if(this.showPages){
                this.searchParams.limit = this.limit;
                this.searchParams.offset = this.offset;
            }
            this.loading = true;
            $.ajax({
                type : 'post',
                url : Global.baseUrl + this.url,
                data: this.searchParams,
                success: function(data) {
                    if(_.isEqual(data.code, 200)){
                        self.tableData = data.rows;
                        self.pageTotal = data.total;
                        self.loading = false;
                    }
                }
            });
        },

    }
})