Vue.component('tree', {
    template: '<div>' +
                '<Tree :data="treeData" @on-select-change="selectTreeNode"></Tree>' +
              '</div>',
    data: function () {
        return {
            treeData: [],
            loading: false,
        }
    },
    props: {
        url: {
            type: String,
            required: true
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
        initTree() {
            this.loadData();
        },

        loadData() {
            var self = this;
            this.loading = true;
            $.ajax({
                type : 'post',
                url : Global.baseUrl + this.url,
                data: this.searchParams,
                success: function(data) {
                    if(_.isEqual(data.code, 200)){
                        self.tableData = data.data;
                        self.loading = false;
                    }
                }
            });
        },

        selectTreeNode(v1, v2, v3){
            console.log(v1, v2, v3);
        },

    }
})