function Zoro(game, spritesheets) {
    this.ctx = game.ctx;
    this.scale = 1.5;
    var framesWidth = [];
    var framesHeight = [];
    this.idle = new Animation(spritesheets["Idle"], 65, 70, 261, 0.5, 4, true, this.scale,  framesWidth, framesHeight);
    this.running = new Animation(spritesheets["Run"], 79, 50, 632, 0.15 ,8, true, this.scale, framesWidth, framesHeight);
    this.dash = new Animation(spritesheets["Dash"], 82, 50, 577, 0.15, 7, true, this.scale,  framesWidth, framesHeight);
    this.guard = new Animation(spritesheets["Guard"], 55, 55, 165, 0.5, 3, true, this.scale,  framesWidth, framesHeight);

    //Skills
    var framesWidth = [51, 79 , 65, 70, 70, 54, 52];
    this.tatsumaki = new Animation(spritesheets["Tatsumaki"], 0, 50, 555, 0.3, 7, true, this.scale, framesWidth, framesHeight);

    var framesWidth = [52, 67, 66, 66, 66, 66];
    //var framesHeight = [23, 46, 71, 78, 104, 62];
    var framesHeight = [25, 59, 91, 107, 141, 145];

    this.particleTatsumaki = new Animation(spritesheets["ParticleTatsumaki"], 0, 0, 383, 0.35, 6, true, this.scale, framesWidth, framesHeight)
    this.offsetX = 0;
    this.offsetY = 0;

    this.skill = false;
    this.skillName = null;
    this.speed = this.scale * 50;
    this.dashSpeed = this.scale * 200;
    this.acceleration = this.scale * 1.5;
    this.actionSheet = this.idle;
    this.particleSheet = null;

    this.actionAnchor = [];
    this.actionAnchor.push(351);
    this.actionAnchor.push(369);
    this.actionAnchor.push(377);

    this.line = createLine();
    this.move = false;
    this.elapsedTime = 0;
    Entity.call(this, game, 0, this.actionAnchor[0]);
}

Zoro.prototype = new Entity();
Zoro.prototype.constructor = Zoro;


/**************************************************************************************
 * Source: http://stackoverflow.com/questions/4270485/drawing-lines-on-html-page
 * */
function createLineElement(x, y, length, angle) {
    var line = document.createElement("div");
    var styles = 'border: 1px solid black; '
        + 'width: ' + length + 'px; '
        + 'height: 0px; '
        + '-moz-transform: rotate(' + angle + 'rad); '
        + '-webkit-transform: rotate(' + angle + 'rad); '
        + '-o-transform: rotate(' + angle + 'rad); '
        + '-ms-transform: rotate(' + angle + 'rad); '
        + 'position: absolute; '
        + 'top: ' + y + 'px; '
        + 'left: ' + x + 'px; ';
    line.setAttribute('style', styles);
    return line;
}

function createLine(x1, y1, x2, y2) {
    var a = x1 - x2,
        b = y1 - y2,
        c = Math.sqrt(a * a + b * b);

    var sx = (x1 + x2) / 2,
        sy = (y1 + y2) / 2;

    var x = sx - c / 2,
        y = sy;

    var alpha = Math.PI - Math.atan2(-b, a);

    return createLineElement(x, y, c, alpha);
};
/**                                        END
 **************************************************************************************/

Zoro.prototype.update = function (){

    //console.log(this.game.clockTick);

    //Move out of canvas
    if (this.x > 960) {
        this.elapsedTime = 0;
        this.move = false;
        this.game.move = false;
        this.game.dash =  false;
        this.skill = false;
        this.game.skill = false;
        this.actionSheet = this.idle;

        this.speed = this.scale * 50;
        this.x = 0;
        this.y = this.actionAnchor[0];
        console.log("Elapsed time: " + this.elapsedTime);
    } else {                             //Inside canvas. Permit to move
        this.elapsedTime++;
        console.log("Elapsed time: " + this.elapsedTime);

        if (this.elapsedTime > 100 && this.elapsedTime <= 200) {     //Run
            this.move = true;
            this.game.move = true;
            this.actionSheet = this.running;
            this.particleSheet = null;
            this.x += this.game.clockTick * (this.speed);
            this.y = this.actionAnchor[1];
        } else if (this.elapsedTime > 200 && this.x <= 300) {       //Dash
            this.x += this.game.clockTick * (this.dashSpeed);
            this.actionSheet = this.dash;
            this.particleSheet = null;
            this.game.dash = true;

        } else if (this.elapsedTime > 300 && this.elapsedTime <= 400) {            //Guard
            //console.log(this.elapsedTime);
            //this.y = this.actionAnchor[2];
            this.speed = this.scale * 50;
            this.actionSheet = this.guard;
            this.particleSheet = null;

            this.game.move = false;
            this.game.dash = false;

            this.y = this.actionAnchor[1];

        } else if (this.elapsedTime > 400 && this.elapsedTime <= 900) {     //Skill - Dragon Twister
            console.log(this.elapsedTime);

                if (this.actionSheet.isDone()) {
                    this.move = true;
                    this.game.move = true;
                    this.actionSheet = this.running;
                    this.particleSheet = null;
                    this.x += this.game.clockTick * (this.speed);
                    this.y = this.actionAnchor[1];
                }


            this.move = false;
            this.game.move = false;
            this.skill = true;
            this.game.skill = true;
            this.actionSheet = this.tatsumaki;

                this.particleSheet = this.particleTatsumaki;
                this.offsetY = 55;

            this.y = this.actionAnchor[2];
            this.offsetX = 9;

        } else if (this.elapsedTime > 1000) {
            this.move = true;
            this.game.move = true;
            this.actionSheet = this.running;
            this.particleSheet = null;
            this.x += this.game.clockTick * (this.speed);
            this.y = this.actionAnchor[1];
        }
    }


    Entity.prototype.update.call(this);

};
Zoro.prototype.draw = function(game) {
    //document.body.appendChild(createLine(10, 450, 1920, 450));
    if (this.actionSheet !== null)
     this.actionSheet.drawFrame(this.game.clockTick, this.game.ctx, this.x, this.y);
    if (this.particleSheet !== null) {
        this.particleSheet.drawFrame(this.game.clockTick, this.game.ctx, this.x - this.offsetX, this.y + this.offsetY);
    }
    Entity.prototype.draw.call(this);
};

