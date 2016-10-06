/**
 * Created by trung on 4/15/2016.
 */
function Prop(game, spriteSheet, x, y, scaleX, scaleY, speed, moveRight, goBack) {
    this.image = spriteSheet;
    this.game = game;
    this.ctx = game.ctx;
    this.x = x;
    this.xCopy = x;
    this.y = y;
    this.scaleX = scaleX;
    this.scaleY = scaleY;
    this.speed = speed;
    this.moveRight = moveRight;
    this.goBack = goBack;
    Entity.call(this, game, x, y);

}
Prop.prototype.constructor = Prop;

Prop.prototype.update = function () {
    if (this.game.move) {
        if (this.moveRight) {
            this.x += this.speed;
        } else {
            this.x -= this.speed;
        }
    }
    if (this.x < this.goBack) {
        this.x = this.xCopy;
    }
}

Prop.prototype.draw = function () {
    this.ctx.drawImage(this.image,
        this.x, this.y, this.scaleX * 0.25, this.scaleY * 0.25);
}
