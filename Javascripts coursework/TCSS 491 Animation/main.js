var AM = new AssetManager();

function Animation(spriteSheet, frameWidth, frameHeight, sheetWidth, frameDuration, frames, loop, scale, framesWidth, framesHeight) {
    this.spriteSheet = spriteSheet;
    this.frameWidth = frameWidth;
    this.frameDuration = frameDuration;
    this.frameHeight = frameHeight;
    this.sheetWidth = sheetWidth;
    this.frames = frames;
    this.totalTime = frameDuration * frames;
    this.elapsedTime = 0;
    this.loop = loop;
    this.scale = scale;
    this.framesWidth = [];
    this.framesHeight = [];

    this.widthAccumulator = 0;
    this.heightAccumulator = 0;

    if (framesWidth.length != 0) {
        this.framesWidth = framesWidth;
        //console.log("Width: " + this.framesWidth);
    }
    if (framesHeight.length != 0) {
        this.framesHeight = framesHeight;
        //console.log("Heights: " + this.framesHeight);
    }
    this.wACopy = 0;
    this.previousFrame = 0;
    this.frame = 0;
    this.reset = false;
}

Animation.prototype.drawFrame = function (tick, ctx, x, y) {
    this.elapsedTime += tick;
    if (this.isDone()) {
        if (this.loop) this.elapsedTime = 0;
    }
    this.previousFrame = this.frame;
    this.frame = this.currentFrame();

    //Reset to the beginning of the first frame if the last frame is reached.
    // Save the last frame position before resetting.
    if (this.frame < this.previousFrame) {
        this.reset = true;
        this.widthAccumulator = 0;
        this.heightAccumulator = 0;
    } else {
        this.reset = false;
    }

    if (this.framesWidth.length != 0 && this.frame >= this.framesWidth.length) {
        this.frame--;
    }
    //console.log("********************************");
    //console.log("Frame: " + this.frame);

    var xindex = 0;
    var yindex = 0;
    xindex = this.frame % this.sheetWidth;
    //console.log("X index: " + xindex);
    if (this.framesWidth.length !== 0) {
        this.frameWidth = this.framesWidth[this.frame];
        //console.log("Width: " + this.frameWidth);
        if (this.previousFrame < this.frame) {
            this.widthAccumulator += this.framesWidth[this.previousFrame];
        }
        //console.log("Width Accumulator: " + this.widthAccumulator);

    }
    if (this.framesHeight.length !== 0) {
        this.frameHeight = this.framesHeight[this.frame];

        if (this.previousFrame < this.frame || this.previousFrame == 0 && this.frame == 0) {
            this.heightAccumulator = this.framesHeight[this.frame];
        }
        //console.log("Height Accumulator: " + this.heightAccumulator);

    }


    yindex = Math.floor(this.frame / this.sheetWidth);

    //console.log("X: " + xindex * this.frameWidth);
    //console.log("Y: " + yindex * this.frameHeight);
    if (this.framesWidth.length != 0 && this.framesHeight.length == 0) {            //Varied width, fixed height
       // console.log("Varied width, fixed height")
        ctx.drawImage(this.spriteSheet, this.widthAccumulator, yindex * this.frameHeight,  // source from image
            this.frameWidth, this.frameHeight,
            x, y,
            this.frameWidth * this.scale,
            this.frameHeight * this.scale);
    }   else if (this.framesWidth.length == 0 && this.framesHeight.length != 0) {       //Fixed width, varied height
        //console.log("fixed width, varied height")
       // y = y - this.heightAccumulator;
        //console.log("Y : " + (y - this.framesHeight[this.frame]) + " heightOffset: " + this.heightAccumulator);

        ctx.drawImage(this.spriteSheet, xindex * this.frameWidth, yindex * this.frameHeight,  // source from image
            this.frameWidth, this.frameHeight,
            x, y - this.framesHeight[this.frame],
            this.frameWidth * this.scale,
            this.frameHeight * this.scale);
    } else if (this.framesWidth.length != 0 && this.framesHeight.length != 0) {     //Varied width and height
        //console.log("Varied width, varied height")
        //y = y - this.heightAccumulator;
        //console.log("Y : " + (y - this.heightAccumulator + this.framesHeight[this.frame]) + " heightOffset: " + this.heightAccumulator);
        ctx.drawImage(this.spriteSheet,this.widthAccumulator, yindex * this.frameHeight,  // source from image
            this.frameWidth, this.frameHeight,
            x,  y - this.framesHeight[this.frame],
            this.frameWidth * this.scale,
            this.frameHeight * this.scale);

    } else {
        //console.log("Varred width, fixed height")
        ctx.drawImage(this.spriteSheet, xindex * this.frameWidth, yindex * this.frameHeight,  // source from image
                this.frameWidth, this.frameHeight,
                x, y,
                this.frameWidth * this.scale,
                this.frameHeight * this.scale);
    }



    //if (this.framesWidth !== null) {
    //    if (this.reset) {      //There has been a reset to the first frame
    //        console.log("RESET *********************");
    //        console.log("X: " + this.wACopy);
    //        ctx.drawImage(this.spriteSheet, this.widthAccumulator, yindex * this.frameHeight,  // source from image
    //            this.frameWidth, this.frameHeight,
    //            x, y,
    //            this.frameWidth * this.scale,
    //            this.frameHeight * this.scale);
    //    } else {
    //        console.log("ACCUMULATOR **********");
    //        console.log("X: " + this.widthAccumulator);
    //
    //        ctx.drawImage(this.spriteSheet, this.widthAccumulator, yindex * this.frameHeight,  // source from image
    //            this.frameWidth, this.frameHeight,
    //            x, y,
    //            this.frameWidth * this.scale,
    //            this.frameHeight * this.scale);
    //    }
    //
    //} else {
    //
    //    ctx.drawImage(this.spriteSheet, xindex * this.frameWidth, yindex * this.frameHeight,  // source from image
    //        this.frameWidth, this.frameHeight,
    //        x, y,
    //        this.frameWidth * this.scale,
    //        this.frameHeight * this.scale);
    //}
}

Animation.prototype.currentFrame = function () {
    return Math.floor(this.elapsedTime / this.frameDuration);
}

Animation.prototype.isDone = function () {
    return (this.elapsedTime >= this.totalTime);
}


// no inheritance
function Background(game, x, goBackX, image){
    this.x = x;
    this.xCopy = x;
    this.goBackX = goBackX,
    this.y = 0;
    this.imageWidth = image.width;
    this.imageHeight = image.height;
    this.speed = 0.1;
    this.image = image;
    this.game = game;
    this.ctx = game.ctx;
};

Background.prototype.draw = function () {
    this.ctx.drawImage(this.image,
        this.x, this.y, 1280, 540);
};

Background.prototype.update = function () {
    if (this.game.move)
        this.x -= this.speed;
    //Move past canvas
    if (this.x < this.goBackX) {
        this.x = this.xCopy;
    }
    //this.game.parallax_x2 -= 1;
};
function Foreground(game, x, goBackX, image) {
    this.x = x;
    this.xCopy = x;
    this.dashSpeed = 5;
    this.speed = 2;
    this.goBackX = goBackX;

    this.y = 0;
    this.scale = 0.5;
    this.image = image;
    this.game = game;
    this.ctx = game.ctx;
};

Foreground.prototype.draw = function () {
    this.ctx.drawImage(this.image,
        this.x, this.y, 1920, 540);
};

Foreground.prototype.update = function () {
    if (this.game.move) {
        if (this.game.dash) {
            this.x -= this.dashSpeed;
        } else {
            this.x -= this.speed;
        }
    }
    if (this.x < this.goBackX) {
        this.x = this.xCopy;
    }
};

//function Foreground1(game, image) {
//    this.x = game.parallax_x2;
//    this.y = game.parallax_y2;
//    this.scale = 0.5;
//    this.image = image;
//    this.game = game;
//    this.ctx = game.ctx;
//};
//
//Foreground1.prototype.draw = function () {
//    this.ctx.drawImage(this.image,
//        this.x, this.y, 960, 540);
//};
//
//Foreground1.prototype.update = function () {
//    this.x -= 2;
//    this.game.parallax_x1 -= 2;
//    if (this.game.parallax_x2 < 0) {
//        this.game.parallax_x2 = 960;
//        this.x = this.game.parallax_x2;
//    }
//};
//function MushroomDude(game, image) {
//    this.animation = new Animation(image, 189, 230, 5, 0.10, 14, true, 1);
//    this.x = 0;
//    this.y = 0;
//    this.speed = 100;
//    this.game = game;
//    this.ctx = game.ctx;
//}
//
//MushroomDude.prototype.draw = function () {
//    this.animation.drawFrame(this.game.clockTick, this.ctx, this.x, this.y);
//}
//
//MushroomDude.prototype.update = function () {
//    if (this.animation.elapsedTime < this.animation.totalTime * 8 / 14)
//        this.x += this.game.clockTick * this.speed;
//    if (this.x > 800) this.x = -230;
//}
//
//
//// inheritance
//function Cheetah(game, image) {
//    this.animation = new Animation(image, 512, 256, 2, 0.05, 8, true, 0.5);
//    this.speed = 350;
//    this.ctx = game.ctx;
//    Entity.call(this, game, 0, 250);
//}
//
//Cheetah.prototype = new Entity();
//Cheetah.prototype.constructor = Cheetah;
//
//Cheetah.prototype.update = function () {
//    this.x += this.game.clockTick * this.speed;
//    if (this.x > 800) this.x = -230;
//    Entity.prototype.update.call(this);
//}
//
//Cheetah.prototype.draw = function () {
//    this.animation.drawFrame(this.game.clockTick, this.ctx, this.x, this.y);
//    Entity.prototype.draw.call(this);
//}
//
//// inheritance
function Sunny(game, image) {
    this.speed = 0.4;
    this.x = 480;
    this.y = 6;
    this.scale = 1.98;
    this.imageWidth = image.width;
    this.imageHeight = image.height;
    this.game = game;
    this.ctx = game.ctx
    this.image = image;
}

Sunny.prototype = new Entity();
Sunny.prototype.constructor = Sunny;

Sunny.prototype.update = function () {
    if (this.game.move)
        this.x -= this.speed;
}

Sunny.prototype.draw = function () {

    this.ctx.drawImage(this.image,
        this.x, this.y, this.imageWidth * this.scale, this.imageHeight * this.scale);
}

//window.onload = function() {
//
//    window.onscroll = function() {
//        var posX = (document.documentElement.scrollLeft) ? document.documentElement.scrollLeft : window.pageXOffset;
//        var posY = (document.documentElement.scrollTop) ? document.documentElement.scrollTop : window.pageYOffset;
//
//        var ground = document.getElementById('ground');
//        var groundparallax = calcParallax(53, 8, posY);
//        ground.style.backgroundPosition = "0 " + groundparallax + "px";
//
//        var clouds = document.getElementById('clouds');
//        var cloudsparallax = calcParallax(400, .5, posY);
//        clouds.style.backgroundPosition = "0 " + cloudsparallax + "px";
//    }
//
//    document.getElementById('javascriptcode').onscroll = function() {
//        var posX = (this.scrollLeft) ? this.scrollLeft : this.pageXOffset;
//        var j = calcParallax(53, 16, posX);
//        console.log('scroll js: '+ j);
//        document.getElementById('javascriptcode').style.backgroundPosition = j + "px 0";
//    }
//}
AM.queueDownload("./img/background.png");
AM.queueDownload("./img/foreground.png");
AM.queueDownload("./img/water.png");
AM.queueDownload("./img/thousand_sunny.png");

AM.queueDownload("./img/Idle.png");
AM.queueDownload("./img/Running.png");
AM.queueDownload("./img/Dash.png");
AM.queueDownload("./img/Guard.png");

//Skill images
AM.queueDownload("./img/Skill-Tatsumaki.png");
AM.queueDownload("./img/Particle-Tatsumaki.png");

//props
AM.queueDownload("./img/barrel.png");


AM.downloadAll(function () {
    var canvas = document.getElementById("gameWorld");

    var ctx = canvas.getContext("2d");
    //
    var gameEngine = new GameEngine();
    //var gameEngine2 = new GameEngine();
    //
    //gameEngine2.init(ctx2);
    //gameEngine2.start();

    gameEngine.init(ctx);
    gameEngine.start();


    var background = AM.getAsset("./img/background.png");
    gameEngine.addEntity(new Background(gameEngine, 1280, 0, background));
    gameEngine.addEntity(new Background(gameEngine, 0, -1280, background));

    gameEngine.addEntity(new Sunny(gameEngine, AM.getAsset("./img/thousand_sunny.png")));

    var water = AM.getAsset("./img/water.png");
    gameEngine.addEntity(new Background(gameEngine, 0, -1920, water));
    gameEngine.addEntity(new Background(gameEngine, 1920, 0, water));

    var foreground = AM.getAsset("./img/foreground.png");
    gameEngine.addEntity(new Foreground(gameEngine, 0, -1920, foreground));
    gameEngine.addEntity(new Foreground(gameEngine, 1920, 0, foreground));

    var characterSheets = {};
    var name = "Idle";
    var spriteSheet = AM.getAsset("./img/Idle.png");
    characterSheets[name] = spriteSheet;
    console.log(characterSheets[name]);

    name = "Run";
    spriteSheet = AM.getAsset("./img/Running.png");
    characterSheets[name] = spriteSheet;
    console.log(characterSheets[name]);

    name = "Dash";
    spriteSheet = AM.getAsset("./img/Dash.png");
    characterSheets[name] = spriteSheet;
    console.log(characterSheets[name]);

    name = "Guard";
    spriteSheet = AM.getAsset("./img/Guard.png");
    characterSheets[name] = spriteSheet;
    console.log(characterSheets[name]);

    name = "Tatsumaki";
    spriteSheet = AM.getAsset("./img/Skill-Tatsumaki.png");
    characterSheets[name] = spriteSheet;
    console.log(characterSheets[name]);

    name = "ParticleTatsumaki";
    spriteSheet = AM.getAsset("./img/Particle-Tatsumaki.png");
    characterSheets[name] = spriteSheet;
    console.log(characterSheets[name]);

    gameEngine.addEntity(new Prop(gameEngine, AM.getAsset("./img/barrel.png"), 970, 400, 140, 190, 2, false, 0))

    var zoro = new Zoro(gameEngine, characterSheets);
    gameEngine.addEntity(zoro);

    console.log("All Done!");
});