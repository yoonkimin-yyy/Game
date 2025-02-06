/*function findInfo(){
    const inputText = document.getElementById('summoner-name');
    const [beforeHash, afterHash] = inputText.value.split('#');
   	
    const riotName = beforeHash.trim();
    const riotTag = afterHash.trim();
    
	const apiKey = "RGAPI-1a7e8ce5-b776-428a-ad2b-63184b2f197c"
	
    $.ajax({
        method : "GET",
        url : `https://asia.api.riotgames.com/riot/account/v1/accounts/by-riot-id/${riotName}/${riotTag}?api_key=${apiKey}`,
        data : {gameName : riotName , tagLine : riotTag},
        headers : {'X-Riot-Token' : apiKey}
    })
    .done(function(msg){
        console.log(msg);
        const puuid = msg.puuid;
    
        $.ajax({
            method : "GET",
            url : `https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/${puuid}/ids?start=0&count=20&api_key=${apiKey}`,
            headers : {'X-Riot-Token' : apiKey}
        })
        .done(function(msg){
            console.log(msg[0]);
            const matchId = msg[0];
    
            $.ajax({
                method : "GET",
                url : `https://asia.api.riotgames.com/lol/match/v5/matches/${matchId}?api_key=${apiKey}`,
                headers : {'X-Riot-Token' : apiKey}
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
}*/










/* 여긴 원민이 형이 한 거 */
function findInfo() {
    const inputText = document.getElementById('summoner-name');
    const [beforeHash, afterHash] = inputText.value.split('#');

    const riotName = beforeHash.trim();
    const riotTag = afterHash.trim();

    // 🔹 1. 소환사 정보 가져오기
    $.ajax({
        method: "GET",
        url: `http://localhost:8080/api/riot/account/${riotName}/${riotTag}`,
    })
    .done(function (msg) {
        console.log("Summoner Info:", msg);
        const puuid = msg.puuid;
		


        // 🔹 2. PUUID로 최근 경기 목록 가져오기
        $.ajax({
            method: "GET",
            url: `http://localhost:8080/api/riot/matches/${puuid}`,
        })
        .done(function (msg) {
            console.log("Match List:", msg);
            const matchId = msg[0];

            // 🔹 3. 경기 상세 정보 가져오기
            $.ajax({
                method: "GET",
                url: `http://localhost:8080/api/riot/match/${matchId}`,
            })
            .done(function (msg) {
                console.log("Match Details:", msg);
                let summonerId = "";
                for (let i = 0; i < 10; i++) {
                    if (msg.info.participants[i].puuid === puuid) {
                        summonerId = msg.info.participants[i].summonerId;
                    }
                }

                // 🔹 4. 소환사 랭크 정보 가져오기
                $.ajax({
                    method: "GET",
                    url: `http://localhost:8080/api/riot/league/entries/${summonerId}`,
                })
                .done(function (msg) {
                    console.log("라이엇이름",riotName,"라이엇태그",riotTag,"큐타입", msg[0].queueType,"이긴판수",msg[0].wins,"진판수",msg[0].losses,
													"티어",msg[0].tier,"랭크",msg[0].rank
					);
					
					const data = {
						riotName: riotName,
						riotTag: riotTag,
						queueType: msg[0].queueType,
						wins: msg[0].wins,
						losses: msg[0].losses,
						tier: msg[0].tier,
						rank: msg[0].rank
					}
					document.getElementById('riot-name').value = riotName;
					document.getElementById('riot-tag').value = riotTag;
					document.getElementById('lol-tier').value = msg[0].tier;
					document.getElementById('lol-rank').value = msg[0].rank;
					document.getElementById('lol-win').value = msg[0].wins;
					document.getElementById('lol-lose').value = msg[0].losses;
					
                });
            });
        });
    });
}


