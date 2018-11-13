local tValues = {}
local nHistWidth = 0
local nHistWidthInverted = 0

local nCurrentIndex
local nMaxIndex
nMax = 0

local bNeedRainlikeScale = false
local bUseAbs = false
local nRainlikeStart = 0
local nRainlikeScale = 0
local nMinimum = 1

local bFirstCycle = true
local nFirstValue

local bBroken = false

local sLinkedGroup
local nLinkedGroupUpdateDivider
local nLastValue
local nLastValueDuration = 0
local bPaused = false

local nSavedResult = 0

outAllTimeMax = 0
outDelta = 0
outSum = 0
outAverage = 0
outSessionSum = 0

local function makeResult(nValue)
    if (nValue < nMinimum) then
        nValue = nMinimum
    end

    local res = nValue
    if (bNeedRainlikeScale) then
        local nRainlikeMax = nRainlikeStart
        local nScaleLocal = nRainlikeScale
        local nMaxLocal = nValue
        while nRainlikeMax < nMaxLocal do
            nRainlikeMax = nRainlikeMax * nScaleLocal
        end
        res = nRainlikeMax
    end

    return res
end

local function init(nValue)
    tValues[0] = 0
    nCurrentIndex = 0
    nMaxIndex = 1
    bFirstCycle = true
    nFirstValue = nValue
    nLastValue = nValue
    nSavedResult = makeResult(nValue)
end

local function getValue()
    local nValue = SELF:GetNumberOption('CurValue')
    return nValue
end

local function lockLinkedGroup()
    if (sLinkedGroup ~= nil) then
        SKIN:Bang('!SetOptionGroup', sLinkedGroup, 'UpdateDivider', '-1')
    end
end

local function unlockLinkedGroup()
    if (sLinkedGroup ~= nil) then
        SKIN:Bang('!SetOptionGroup', sLinkedGroup, 'UpdateDivider', nLinkedGroupUpdateDivider)
        SKIN:Bang('!UpdateMeasureGroup', sLinkedGroup)
        SKIN:Bang('!UpdateMeterGroup', sLinkedGroup)
    end
end

function Initialize()
    nHistWidth = SELF:GetNumberOption('HistWidth', nHistWidth)
    if (nHistWidth <= 1) then
        SKIN:Bang('!Log', 'HistWidth=' .. SELF:GetOption('HistWidth') .. ' is not valid. Aborting', 'Error')
        bBroken = true
        return
    end
    nCurrentIndex = nHistWidth
    nHistWidthInverted = 1 / nHistWidth

    bNeedRainlikeScale = (SELF:GetNumberOption('UseRainlikeScale', 0) ~= 0)

    if (bNeedRainlikeScale) then
        nRainlikeStart = SELF:GetNumberOption('RainlikeScaleStart', 2)
        if (nRainlikeStart <= 1) then
            SKIN:Bang('!Log', ' RainlikeScaleStart=' .. SELF:GetOption('RainlikeScaleStart') .. ' is not valid. Falling to default 2', 'Warning')
            nRainlikeStart = 2
        end

        nRainlikeScale = SELF:GetNumberOption('RainlikeScaleStep', 2)
        if (nRainlikeScale <= 1) then
            SKIN:Bang('!Log', ' RainlikeScaleStep=' .. SELF:GetOption('RainlikeScaleStep') .. ' is not valid. Falling to default 2', 'Warning')
            nRainlikeScale = 2
        end
    end

    nMinimum = SELF:GetNumberOption('Minimum')

    local nUseAbs = SELF:GetNumberOption('FindAbsMax')
    bUseAbs = nUseAbs ~= 0

    init(getValue())

    sLinkedGroup = SELF:GetOption('LinkedGroup')
    if (sLinkedGroup == '') then
        sLinkedGroup = nil
    else
        nLinkedGroupUpdateDivider = math.floor(SELF:GetNumberOption('LinkedGroupUpdateDivider', 1))
        if (nLinkedGroupUpdateDivider < 1) then
            nLinkedGroupUpdateDivider = 1
        end

        --lockLinkedGroup()
    end

    return
end

-- @const
local function findMax()
    local tValuesLocal = tValues
    local nTempMax = 0
    local nTempMaxIndex = 0
    if (bFirstCycle == false) then
        for i = nCurrentIndex + 1, nHistWidth, 1 do
            local value = tValuesLocal[i];
            if (value >= nTempMax) then
                nTempMax = value
                nTempMaxIndex = i
            end
        end
    end
    for i = 1, nCurrentIndex, 1 do
        local value = tValuesLocal[i];
        if (value >= nTempMax) then
            nTempMax = value
            nTempMaxIndex = i
        end
    end
    return nTempMax, nTempMaxIndex
end

function Update()
    if (bBroken) then
        return 'broken'
    end

    local nValue = getValue()

    if (tValues[0] == 0) then
        if (nValue == nFirstValue) then
            return nSavedResult
        else
            unlockLinkedGroup()
        end
    end

    if (nLastValue == nValue) then
        if (bPaused) then
            return nSavedResult
        end
        if (nLastValueDuration >= nHistWidth) then
            if (sLinkedGroup ~= nil) then
                lockLinkedGroup()
            end
            bPaused = true
            return nSavedResult
        end
        nLastValueDuration = nLastValueDuration + 1
    else
        if (bPaused) then
            unlockLinkedGroup()
            bPaused = false
            init(nLastValue)
            tValues[1] = nLastValue
            nCurrentIndex = 1
        end
        nLastValueDuration = 1
        nLastValue = nValue
    end

    local bChanges = false
    local tValuesLocal = tValues

    nCurrentIndex = nCurrentIndex + 1
    if (nCurrentIndex > nHistWidth) then
        nCurrentIndex = 1
        bFirstCycle = false
    end
    if (bFirstCycle) then
        tValues[0] = nCurrentIndex
    end

    outDelta = nValue - (tValuesLocal[nCurrentIndex] or nFirstValue)
    outSum = outSum - (tValuesLocal[nCurrentIndex] or 0) + nValue
    outAverage = outSum * nHistWidthInverted
    outSessionSum = outSessionSum + nValue

    if (bUseAbs and nValue < 0) then
        nValue = -nValue
    end
    tValuesLocal[nCurrentIndex] = nValue

    if (nValue >= nMax) then
        bChanges = true
        nMax = nValue
        nMaxIndex = nCurrentIndex
    elseif (nMaxIndex == nCurrentIndex) then
        bChanges = true
        nMax, nMaxIndex = findMax()
    end

    if (bChanges == false) then
        return nSavedResult
    end

    if (nMax > outAllTimeMax) then
        outAllTimeMax = nMax
    end

    nSavedResult = makeResult(nMax)
    return nSavedResult
end
