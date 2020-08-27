


    // var client = filestack.init(FILESTACK_KEY);


    var client = filestack.init("");

    //The function pickMark()
    let imgUrl;
    let imageSelection = document.querySelector('#sign_page_banner_image');
    console.log(imageSelection);

    // function updateImage(){
    //     imgUrl = imageSelection.value;
    // }

    function pickMark() {
        console.log("Picking Watermark");
        //Limiting file types and number for uploading in the picker
        client.pick({
            //Only accepting files with a mimetype 'image/*'
            accept: 'image/*',
            //Only accepting at most 1 file
            maxFiles:1,
        }).then(function(result) {//Taking the result object in as 'result'
            //Putting the result in a string, and printing it to the console
            // console.log(result);
            console.log(result.filesUploaded[0].url);

            // imgUrl = result.filesUploaded[0].url;
            // imgUrl = (JSON.stringify(result.filesUploaded[0].url));
            imgUrl = result.filesUploaded[0].url;
            console.log(imgUrl);
            // document.getElementById("submit").addEventListener("click", updateImage);
            document.getElementById('sign_page_banner_image').value = imgUrl;
            // imageSelection = document.querySelector('#imagePath');
            console.log(imageSelection);
            console.log(imgUrl);
        })
    }