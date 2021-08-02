package dev.fgiris.composeanimations.data

object AnimationCodeSamples {
    val animateAsState = "// Initially the alpha value will be 0\n" +
            "val animationTargetState = remember { mutableStateOf(0f) }\n" +
            "\n" +
            "val animatedFloatState = animateFloatAsState(\n" +
            "    // Whenever the target value changes, new animation\n" +
            "    // will start to the new target value\n" +
            "    targetValue = animationTargetState.value,\n" +
            "    animationSpec = tween(durationMillis = 3000)\n" +
            ")\n" +
            "\n" +
            "val onSurface = MaterialTheme.colors.onSurface\n" +
            "\n" +
            "Canvas(\n" +
            "    modifier = Modifier\n" +
            "        .fillMaxWidth()\n" +
            "        .fillMaxHeight()\n" +
            "        .clickable {\n" +
            "            // Change the target state to start the animation\n" +
            "            animationTargetState.value = 1f\n" +
            "        }\n" +
            ") {\n" +
            "    drawCircle(\n" +
            "        color = onSurface,\n" +
            "        radius = 50f,\n" +
            "        // The circle will fade in with the given animation value\n" +
            "        alpha = animatedFloatState.value\n" +
            "    )\n" +
            "}"

    val updateTransition = "// Need to remember in order to prevent setting\n" +
            "// the same state value to the transition during\n" +
            "// recomposition.\n" +
            "val animationTargetState = remember {\n" +
            "    mutableStateOf(AnimationState.START)\n" +
            "}\n" +
            "\n" +
            "// Any state change will trigger animations which\n" +
            "// are created with this transition to the new state\n" +
            "val transition = updateTransition(\n" +
            "    targetState = animationTargetState.value\n" +
            ")\n" +
            "\n" +
            "val circleAlpha = transition.animateFloat(\n" +
            "    transitionSpec = { tween(durationMillis = 3000) }\n" +
            ") {\n" +
            "    if (it == AnimationState.START) 0f else 1f\n" +
            "}\n" +
            "\n" +
            "val circleRadius = transition.animateFloat(\n" +
            "    transitionSpec = { tween(durationMillis = 3000) }\n" +
            ") {\n" +
            "    if (it == AnimationState.START) 10f else 50f\n" +
            "}\n" +
            "\n" +
            "val onSurface = MaterialTheme.colors.onSurface\n" +
            "\n" +
            "Canvas(\n" +
            "    modifier = Modifier\n" +
            "        .fillMaxWidth()\n" +
            "        .fillMaxHeight()\n" +
            ") {\n" +
            "    drawCircle(\n" +
            "        color = onSurface,\n" +
            "        radius = circleRadius.value,\n" +
            "        alpha = circleAlpha.value\n" +
            "    )\n" +
            "\n" +
            "    // Set animation state value to another state to trigger the animation\n" +
            "    animationTargetState.value = AnimationState.END\n" +
            "}"

    val rememberInfiniteTransition = "val infiniteTransition = rememberInfiniteTransition()\n" +
            "val infinitelyAnimatedFloat = infiniteTransition.animateFloat(\n" +
            "    initialValue = 0f,\n" +
            "    targetValue = 1f,\n" +
            "    animationSpec = infiniteRepeatable(\n" +
            "        animation = tween(),\n" +
            "        // The value will infinitely repeat from 0 to 1 and 1 to 0\n" +
            "        repeatMode = RepeatMode.Reverse\n" +
            "    )\n" +
            ")\n" +
            "\n" +
            "val onSurface = MaterialTheme.colors.onSurface\n" +
            "\n" +
            "Canvas(\n" +
            "    modifier = Modifier\n" +
            "        .fillMaxWidth()\n" +
            "        .fillMaxHeight()\n" +
            ") {\n" +
            "    drawCircle(\n" +
            "        color = onSurface,\n" +
            "        radius = 10f,\n" +
            "        // The circle will blink infinitely with the animation specs above\n" +
            "        alpha = infinitelyAnimatedFloat.value\n" +
            "    )\n" +
            "}"

    val animatable = "// This scope will be canceled when the composable leaves the composition\n" +
            "val animationScope = rememberCoroutineScope()\n" +
            "\n" +
            "// You could also give an offset as an initial value and\n" +
            "// Offset.VectorConverter as a typeConverter. For the simplicity\n" +
            "// lets just use a different float value for x and y\n" +
            "val animatableX = remember { Animatable(initialValue = 0f) }\n" +
            "val animatableY = remember { Animatable(initialValue = 0f) }\n" +
            "\n" +
            "val onSurface = MaterialTheme.colors.onSurface\n" +
            "\n" +
            "Canvas(\n" +
            "    modifier = Modifier\n" +
            "        .fillMaxWidth()\n" +
            "        .fillMaxHeight()\n" +
            "        .clickable {\n" +
            "            animationScope.launch {\n" +
            "                // Start the animations without blocking each other\n" +
            "                // On each click x and y values will be created randomly\n" +
            "                launch {\n" +
            "                    animatableX.animateTo(\n" +
            "                        targetValue = (0..1000)\n" +
            "                            .random()\n" +
            "                            .toFloat(),\n" +
            "                        animationSpec = tween(durationMillis = 1000)\n" +
            "                    )\n" +
            "                }\n" +
            "\n" +
            "                launch {\n" +
            "                    animatableY.animateTo(\n" +
            "                        targetValue = (0..1000)\n" +
            "                            .random()\n" +
            "                            .toFloat(),\n" +
            "                        animationSpec = tween(durationMillis = 1000)\n" +
            "                    )\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            ") {\n" +
            "    drawCircle(\n" +
            "        color = onSurface,\n" +
            "        radius = 10f,\n" +
            "        center = Offset(\n" +
            "            x = animatableX.value,\n" +
            "            y = animatableY.value\n" +
            "        )\n" +
            "    )\n" +
            "}\n"

    val targetBasedAnimation = "val targetBasedAnimation = remember {\n" +
            "    TargetBasedAnimation(\n" +
            "        animationSpec = tween(2000),\n" +
            "        typeConverter = Float.VectorConverter,\n" +
            "        initialValue = 0f,\n" +
            "        targetValue = 1000f\n" +
            "    )\n" +
            "}\n" +
            "\n" +
            "// We will manually handle the play time of the animation\n" +
            "var playTime = remember { 0L }\n" +
            "\n" +
            "// Do not use LaunchedEffect for the callback events such as when user clicks\n" +
            "// to start the animation again. Instead use rememberCoroutineScope.\n" +
            "val animationScope = rememberCoroutineScope()\n" +
            "\n" +
            "var animationState by remember { mutableStateOf(TargetAnimationState.PAUSED) }\n" +
            "var animationValue by remember { mutableStateOf(0f) }\n" +
            "\n" +
            "val onSurface = MaterialTheme.colors.onSurface\n" +
            "\n" +
            "val onClick: () -> Unit = {\n" +
            "    // Toggle animation state\n" +
            "    animationState = when (animationState) {\n" +
            "        TargetAnimationState.RUNNING -> TargetAnimationState.PAUSED\n" +
            "        TargetAnimationState.PAUSED -> TargetAnimationState.RUNNING\n" +
            "    }\n" +
            "\n" +
            "    animationScope.launch {\n" +
            "        // Need to extract the already played time\n" +
            "        // If user pauses and resumes the animation, shifting start time by\n" +
            "        // play time will make sure that the animation will continue from the\n" +
            "        // last played time\n" +
            "        val startTime = withFrameNanos { it } - playTime\n" +
            "\n" +
            "        // Only continue animating if the state is running\n" +
            "        while (animationState == TargetAnimationState.RUNNING) {\n" +
            "            playTime = withFrameNanos { it } - startTime\n" +
            "            animationValue = targetBasedAnimation.getValueFromNanos(playTime)\n" +
            "        }\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "Canvas(\n" +
            "    modifier = Modifier\n" +
            "        .fillMaxWidth()\n" +
            "        .fillMaxHeight()\n" +
            "        .clickable(onClick = onClick)\n" +
            ") {\n" +
            "    drawCircle(\n" +
            "        color = onSurface,\n" +
            "        radius = 10f,\n" +
            "        center = Offset(\n" +
            "            x = animationValue,\n" +
            "            y = animationValue\n" +
            "        )\n" +
            "    )\n" +
            "}"

    val decayAnimation = "val decayAnimation = remember {\n" +
            "    DecayAnimation(\n" +
            "        animationSpec = FloatExponentialDecaySpec(\n" +
            "            // How quick the animation will stop\n" +
            "            frictionMultiplier = 0.7f\n" +
            "        ),\n" +
            "        initialValue = 0f,\n" +
            "        initialVelocity = 600f\n" +
            "    )\n" +
            "}\n" +
            "\n" +
            "// We will manually handle the play time of the animation\n" +
            "var playTime by remember { mutableStateOf(0L) }\n" +
            "\n" +
            "val animationScope = rememberCoroutineScope()\n" +
            "var animationState by remember { mutableStateOf(DecayAnimationState.PAUSED) }\n" +
            "var animationValue by remember { mutableStateOf(0f) }\n" +
            "\n" +
            "val onClick: () -> Unit = {\n" +
            "    // Toggle animation state\n" +
            "    animationState = when (animationState) {\n" +
            "        DecayAnimationState.RUNNING -> DecayAnimationState.PAUSED\n" +
            "        DecayAnimationState.PAUSED -> DecayAnimationState.RUNNING\n" +
            "    }\n" +
            "\n" +
            "    animationScope.launch {\n" +
            "        // Need to extract the already played time\n" +
            "        // If user pauses and resumes the animation, shifting start time by\n" +
            "        // play time will make sure that the animation will continue from the\n" +
            "        // last played time\n" +
            "        var startTime = withFrameNanos { it } - playTime\n" +
            "\n" +
            "        // Only continue animating if the state is running\n" +
            "        while (animationState == DecayAnimationState.RUNNING) {\n" +
            "            // If the animation is already ended then reset the start time\n" +
            "            // in order animation to start again\n" +
            "            if (decayAnimation.isFinishedFromNanos(playTime)) startTime = withFrameNanos { it }\n" +
            "\n" +
            "            playTime = withFrameNanos { it } - startTime\n" +
            "            animationValue = decayAnimation.getValueFromNanos(playTime)\n" +
            "        }\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "Box(\n" +
            "    modifier = Modifier\n" +
            "        .clickable(onClick = onClick)\n" +
            "        .fillMaxWidth()\n" +
            "        .fillMaxHeight()\n" +
            ") {\n" +
            "    Image(\n" +
            "        painter = painterResource(id = R.drawable.ic_shopping_cart),\n" +
            "        contentDescription = \"\",\n" +
            "        modifier = Modifier.offset(\n" +
            "            x = animationValue.dp,\n" +
            "            y = 250.dp\n" +
            "        )\n" +
            "    )\n" +
            "}"
}