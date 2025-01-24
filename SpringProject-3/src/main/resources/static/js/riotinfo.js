function findInfo(){
    const inputText = document.getElementById('summoner-name');
    const [beforeHash, afterHash] = inputText.value.split('#');
    
    const riotName = beforeHash.trim();
    const riotTag = afterHash.trim();
    
    $.ajax({
        method : "GET",
        url : `https://asia.api.riotgames.com/riot/account/v1/accounts/by-riot-id/${riotName}/${riotTag}?api_key=RGAPI-2405bbcf-a7b7-4426-80c9-8eabe10faa0d`,
        data : {gameName : riotName , tagLine : riotTag},
        headers : {'X-Riot-Token' : "RGAPI-2405bbcf-a7b7-4426-80c9-8eabe10faa0d"}
    })
    .done(function(msg){
        console.log(msg);
        const puuid = msg.puuid;
    
        $.ajax({
            method : "GET",
            url : `https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/${puuid}/ids?start=0&count=20&api_key=RGAPI-2405bbcf-a7b7-4426-80c9-8eabe10faa0d`,
            headers : {'X-Riot-Token' : "RGAPI-2405bbcf-a7b7-4426-80c9-8eabe10faa0d"}
        })
        .done(function(msg){
            console.log(msg[0]);
            const matchId = msg[0];
    
            $.ajax({
                method : "GET",
                url : `https://asia.api.riotgames.com/lol/match/v5/matches/${matchId}?api_key=RGAPI-2405bbcf-a7b7-4426-80c9-8eabe10faa0d`,
                headers : {'X-Riot-Token' : "RGAPI-2405bbcf-a7b7-4426-80c9-8eabe10faa0d"}
            })
            .done(function(msg){
                console.log(msg);
                let summonerId = "";
                for(let i = 0; i<10; i++){
                    if(msg.info.participants[i].puuid == puuid){
                        console.log(msg.info.participants[i].summonerId);
                        summonerId = msg.info.participants[i].summonerId;
                    }
                }
                $.ajax({
                    method : "GET",
                    url : `http://localhost:8080/riot/api/league/entries/${summonerId}`,
                    
                })
                .done(function(msg){
                    console.log("Riot API Response:", msg);
                })
            })
        })
    })
}