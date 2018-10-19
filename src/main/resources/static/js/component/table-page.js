Vue.component('table-page', {
    template: '<div>' +
                '<i-table border stripe ref="selection" :loading="loading" :columns="columns" :data="tableData" @on-selection-change="selChange"></i-table>' +
                '<Page show-total v-if="showPages" :total="pageTotal" :page-size="pageSize" @on-change="pageChange" style="float:left;margin-top:10px;"/>' +
              '</div>',
    data: function () {
        return {
            tableData: [],
            loading: false,
            pageTotal: 0,
            selectRows: [],
            // 分页需要的参数
            pageNum: 1,
            pageSize: ''
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
        pageSize: {
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
        this.loadData();
    },
    computed: {

    },
    methods: {
        pageChange(pageNum) {
            this.pageNum = pageNum;
            this.loadData();
        },

        selChange(selection) {
            let rowsId = [];
            _.forEach(selection, function(row) {
                rowsId.push(row.id);
            });
            this.selectRows = rowsId;
        },

        loadData(tbSearchParams) {
            var self = this;
            // 如果需要分页，把分页数据添加到参数中
            if(this.showPages){
                this.searchParams.pageNum = this.pageNum;
                this.searchParams.pageSize = this.pageSize;
            }
            if(tbSearchParams){
                this.searchParams = _.assign(this.searchParams, tbSearchParams);
            }
            this.loading = true;
            $.ajax({
                type : 'post',
                url : Global.baseUrl + this.url,
                data: this.searchParams,
                success: function(data) {
                    if(_.isEqual(data.code, CODE.SUCCESS_CODE)){
                        self.tableData = data.rows;
                        self.pageTotal = data.total;
                        self.loading = false;
                    }
                }
            });
        },

    }
})