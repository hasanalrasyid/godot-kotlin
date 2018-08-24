package stages.shmup

import godot.Area2D
import godot.core.Vector2

class Bullet: Area2D() {
    var speed = 1000

    override fun _process(delta: Float) {
        position -= Vector2(0, speed * delta)
    }
}