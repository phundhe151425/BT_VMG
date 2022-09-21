

new Vue({
    el: '#app',
    data: {
        title: "Samsung",
        link: "https://drive.google.com/drive/folders/1fLn7nlD_c6r7woEr_RgpUt78xrk6D75-",
        maHTML: '<a href="adadsfdsafdsa"> GEt Password </a>',
    },
    methods: {
        
        sayHello: function(){
            document.write("Say Hello")
        }, 
        showPost: function(){
            this.title = "Content Nguyenx Dinh Phu"
            return this.title;
        }
        
    }
});

new Vue({
    el: '.obj',
    data: {
        a: 0,
        b: 0,
    },
    computed: {
        giaTriA: function(){
            console.log("function A")
            return this.a;
        },
        giaTriB: function(){
            console.log("function B")
            return this.b;
        },
    },
    methods:{
        
        // tinhToaDo: function(event){
        //     this.x = event.clientX;
        //     this.y = event.clientY;
        // },
        banPhim: function(){
            alert("Nguyen Dinh Phu");
        }
        
    }
})
