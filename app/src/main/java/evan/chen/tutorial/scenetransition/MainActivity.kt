package evan.chen.tutorial.scenetransition

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.*
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private var sceneRoot: ViewGroup? = null

    private lateinit var scene1: Scene
    private lateinit var scene2: Scene
    private lateinit var scene3: Scene

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sceneRoot = scene_root

        scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.scene1, this)
        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.scene2, this)

        scene3 = Scene.getSceneForLayout(sceneRoot, R.layout.scene3, this)



        scene1Button.setOnClickListener {
            // 到Scene1
            TransitionManager.go(scene1)
        }

        scene2Button.setOnClickListener {
            // TransitionManager.go(scene2)
            // 將Scene2改成由transitionManagerForScene2來決定動畫方式
            val transitionManagerForScene2 = TransitionInflater.from(this)
                    .inflateTransitionManager(R.transition.scene2_transition_manager, sceneRoot)
            transitionManagerForScene2.transitionTo(scene2)
        }

        scene3Button.setOnClickListener {
            // 到Scene3
            TransitionManager.go(scene3)
        }

        scene4Button.setOnClickListener {
            //不使用Scene做為View的改變。在TransitionManager.beginDelayedTransition，調整View的值
            TransitionManager.beginDelayedTransition(sceneRoot)
            //在這裡調整結束layout。將藍色圓圈放大2倍。
            val square = sceneRoot!!.findViewById<View>(R.id.transition_oval_blue)
            val params = square.layoutParams
            params.width = params.width * 2
            params.height = params.height * 2
            square.layoutParams = params
        }
    }
}

