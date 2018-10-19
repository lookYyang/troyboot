Vue.component('tree', {
    template: '<div>' +
                '<Tree :data="treeData" :show-checkbox="showCheckbox" :multiple="multiple" @on-select-change="selChange" empty-text="没有数据"></Tree>' +
              '</div>',
    data: function () {
        return {
            treeData: [],
            selectNode: {},
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
        },
        // 是否显示多选框
        showCheckbox: {
            type: Boolean,
            default(){
                return false;
            }
        },
        // 是否支持多选
        multiple: {
            type: Boolean,
            default(){
                return false;
            }
        },
        // 是否支持异步加载
        asyncLoading: {
            type: Boolean,
            default(){
                return false;
            }
        },
        cacheNode: {
            type: Function,
        }
    },
    mounted(){
        this.loadData();
    },
    computed: {

    },
    methods: {
        async loadData() {
            var self = this;
            if(_.isEmpty(this.url))
                return;
            await $.ajax({
                type : 'get',
                url : Global.baseUrl + this.url,
                success: function(data) {
                    if(_.isEqual(data.code, CODE.SUCCESS_CODE)){
                        self.treeData = data.data;
                    }
                }
            });
        },

        selChange(node){
            console.log(node);
            this.selectNode = node;
            this.$emit('cache-node', node);
        },

        // // 异步加载的方法
        // asyncLoadingData (item, callback) {
        //     if(!this.asyncLoading) return;
        //     setTimeout(() => {
        //         const data = [
        //             {
        //                 title: 'children',
        //                 loading: false,
        //                 children: []
        //             },
        //             {
        //                 title: 'children',
        //                 loading: false,
        //                 children: []
        //             }
        //         ];
        //         callback(data);
        //     }, 1000);
        // }
    }
})