var app = new Vue({ 
    el: '#app', //옵션    
    data: {
        list: [
            {id: 1, name: '슬라임', hp: 100},
            {id: 2, name: '고블린', hp: 200},
            {id: 3, name: '드래곤', hp: 500}
        ]   
    },
    methods: {
        doAttack: function(index) {
            this.list[index].hp -= 10
        }
    }
    
    /*
        mounted: function(){
        this.scroll = 100 //요소의 스크롤양 조작하기
    },
    created: function(){
        //이 인스턴스의 생성과 초기화가 종료되었을 때 console에 created 찍힘
        console.log('created') 
    }
    */
});