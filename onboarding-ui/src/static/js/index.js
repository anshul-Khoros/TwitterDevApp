import '../scss/styles.scss';

var myRequest = new XMLHttpRequest();
            const url = 'http://localhost:8080/api/1.0/twitter/timeline';
            myRequest.open('GET', url);

            myRequest.onreadystatechange = function(){
            if (myRequest.readyState === 4) {
                let allTweets = JSON.parse(myRequest.responseText);

                console.log("got all tweet", allTweets);

                let dateOptions = { month: 'short', day: 'numeric' };
                let tweetsDiv = document.getElementById("tweets");

                allTweets.forEach((tweet, i)=>{
                    let div = document.createElement('div');

                    let imgDiv = document.createElement('div');
                    imgDiv.classList.add("img-container");

                    var img = document.createElement("img");
                    img.setAttribute('src', tweet.user.profileImageUrl);
                    img.setAttribute('class', 'profile-img');
                    imgDiv.appendChild(img);

                    let nameSpan = document.createElement('span');
                    nameSpan.classList.add("user-name");
                    nameSpan.textContent = tweet.user.name
                    imgDiv.appendChild(nameSpan);

                    div.appendChild(imgDiv);

                    let msgDiv = document.createElement('div');
                    msgDiv.classList.add("msg-container");

                    let span = document.createElement('span');
                    span.classList.add("tweet-date");
                    span.textContent = new Date(tweet.createdAt).toLocaleDateString('en-US', dateOptions);
                    msgDiv.appendChild(span);

                    let anchorTag = document.createElement('a');
                    anchorTag.target = '_blank';
                    anchorTag.href = tweet.tweetUrl;
                    anchorTag.textContent = tweet.message;
                    msgDiv.appendChild(anchorTag);

                    div.appendChild(msgDiv);

                    div.classList.add("tweet");

                    if(i%2 == 0){
                        div.classList.add("even-tweet");
                    } else{
                        div.classList.add("odd-tweet");
                    }

                    tweetsDiv.appendChild(div);
                })
            }
         };
            myRequest.send();